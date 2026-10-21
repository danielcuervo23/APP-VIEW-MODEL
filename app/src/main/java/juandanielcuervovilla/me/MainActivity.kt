package juandanielcuervovilla.me

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.activity.compose.setContent

private val Ink = Color(0xFF18233D)
private val Purple = Color(0xFF6657D9)
private val Lavender = Color(0xFFEDEBFF)
private val Muted = Color(0xFF667085)
private val Canvas = Color(0xFFF7F8FC)

class MainActivity : androidx.activity.ComponentActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Canvas
                ) {
                    AprendeViewModelApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AprendeViewModelApp(mainViewModel: MainViewModel = viewModel()) {
    var showExample by rememberSaveable { mutableStateOf(false) }
    val count by mainViewModel.counter.collectAsState()

    Scaffold(
        containerColor = Canvas,
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Layers, contentDescription = null, tint = Purple)
                        Text(
                            "  Aprende ViewModel",
                            color = Ink,
                            fontWeight = FontWeight.Bold,
                            fontSize = 19.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Canvas)
            )
        }
    ) { innerPadding ->
        if (showExample) {
            ExampleScreen(
                count = count,
                onIncrement = mainViewModel::increment,
                onDecrement = mainViewModel::decrement,
                onReset = mainViewModel::reset,
                onBack = { showExample = false },
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            HomeScreen(
                onOpenExample = { showExample = true },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun HomeScreen(onOpenExample: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 22.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .background(
                    Brush.linearGradient(listOf(Color(0xFF6557D8), Color(0xFF8779F4))),
                    RoundedCornerShape(28.dp)
                )
                .padding(24.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Icon(Icons.Default.Smartphone, contentDescription = null, tint = Color.White, modifier = Modifier.size(34.dp))
                Text("Tu interfaz y tus datos, en equipo.", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold, lineHeight = 31.sp)
                Text(
                    "Descubre cómo ViewModel ayuda a organizar una app Android.",
                    color = Color(0xFFF0EEFF),
                    fontSize = 15.sp,
                    lineHeight = 22.sp
                )
            }
        }

        Text("¿Qué es un ViewModel?", color = Ink, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(
            "Es una clase que guarda y administra los datos relacionados con una pantalla. Separa el estado de la interfaz y ayuda a conservarlo cuando Android recrea la Activity, por ejemplo, al girar el dispositivo.",
            color = Muted, fontSize = 15.sp, lineHeight = 23.sp
        )

        InfoCard(
            icon = "01",
            title = "Separa responsabilidades",
            body = "La pantalla muestra la información; el ViewModel mantiene y actualiza el estado."
        )
        InfoCard(
            icon = "02",
            title = "Sobrevive a cambios de configuración",
            body = "El ViewModel permanece cuando la Activity se recrea, como al girar el teléfono."
        )
        InfoCard(
            icon = "03",
            title = "Facilita el mantenimiento",
            body = "El código queda más organizado y es más fácil de probar."
        )

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onOpenExample,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Purple)
        ) {
            Text("Probar ejemplo interactivo", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.padding(start = 8.dp))
        }
        Text("Kotlin · Jetpack Compose · 1 ViewModel · 2 vistas", color = Muted, fontSize = 12.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun InfoCard(icon: String, title: String, body: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier.size(42.dp).background(Lavender, RoundedCornerShape(13.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(icon, color = Purple, fontWeight = FontWeight.Bold)
            }
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(title, color = Ink, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(body, color = Muted, fontSize = 13.sp, lineHeight = 19.sp)
            }
        }
    }
}

@Composable
private fun ExampleScreen(
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onReset: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 22.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        OutlinedButton(onClick = onBack, shape = RoundedCornerShape(14.dp)) {
            Icon(Icons.Default.ArrowBack, contentDescription = null)
            Text("  Volver a la explicación")
        }

        Text("Ejemplo práctico", color = Ink, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text(
            "Este contador guarda su número en MainViewModel. Pulsa los botones y observa cómo cambia el estado.",
            color = Muted, fontSize = 15.sp, lineHeight = 22.sp
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Box(
                    modifier = Modifier.size(68.dp).background(Lavender, RoundedCornerShape(22.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Code, contentDescription = null, tint = Purple, modifier = Modifier.size(34.dp))
                }
                Text("VALOR ACTUAL", color = Muted, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
                Text(count.toString(), color = Purple, fontSize = 64.sp, fontWeight = FontWeight.ExtraBold)
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    OutlinedButton(
                        onClick = onDecrement,
                        enabled = count > 0,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.weight(1f).height(50.dp)
                    ) { Text("−  Restar", fontSize = 15.sp) }
                    Button(
                        onClick = onIncrement,
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Purple),
                        modifier = Modifier.weight(1f).height(50.dp)
                    ) { Text("+  Sumar", fontSize = 15.sp) }
                }
                OutlinedButton(onClick = onReset, shape = RoundedCornerShape(14.dp)) {
                    Icon(Icons.Default.Refresh, contentDescription = null)
                    Text("  Reiniciar contador")
                }
            }
        }

        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEAF7F0))
        ) {
            Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("¿Qué está pasando?", color = Color(0xFF176B46), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(
                    "1. La pantalla llama a una función del ViewModel.\n2. MainViewModel actualiza un StateFlow.\n3. Compose observa el valor y vuelve a dibujar el contador.\n\nPrueba girar el dispositivo: el ViewModel conserva el valor mientras siga viva su instancia.",
                    color = Color(0xFF315C49), fontSize = 14.sp, lineHeight = 21.sp
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Text(
            "Nota: ViewModel sobrevive a cambios de configuración, pero no garantiza conservar datos tras cerrar el proceso de la app.",
            color = Muted, fontSize = 12.sp, lineHeight = 18.sp
        )
    }
}
