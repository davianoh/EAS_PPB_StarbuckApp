package com.example.myapp
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.ui.text.font.FontWeight
import android.content.res.Configuration.UI_MODE_NIGHT_YES

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myapp.ui.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldOnBoarding by rememberSaveable { mutableStateOf(true) }
    var shouldLogIn by rememberSaveable { mutableStateOf(true) }
    var shouldHome by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldOnBoarding){
            OnBoardingScreen(onContinueClicked = { shouldOnBoarding = false })
        } else if (shouldLogIn) {
            LoginScreen(onContinueClicked = { shouldLogIn = false })
        } else {
            HomeScreen(onContinueClicked = { shouldHome = false })
        }
    }

}

@Preview
@Composable
fun MyAppPreview() {
    MyAppTheme {
        MyApp(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun OnBoardingScreen(onContinueClicked: () -> Unit,
             modifier: Modifier = Modifier){
    Image(
        painter = painterResource(id = R.drawable.onboarding_eas),
        contentDescription = "login image",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
    Column(
        modifier = modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(75.dp))
        Text(text = "Welcome To", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Text(text = "The Starbuck App", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(200.dp))
        Image(painter = painterResource(id = R.drawable.starbuck_logo), contentDescription = "starbuck_logo", modifier = Modifier
            .size(200.dp)
        )
        Spacer(modifier = Modifier.height(100.dp))
        TextButton(onClick = onContinueClicked) {
            Text(text = "Next", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }

}

@Composable
fun LoginScreen(onContinueClicked: () -> Unit,
                     modifier: Modifier = Modifier) {

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var namaPengguna by remember {
        mutableStateOf("")
    }
    var mobileNumber by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }


    Image(
        painter = painterResource(id = R.drawable.signup_eas),
        contentDescription = "login image",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
//    Image(
//        painter = painterResource(id = R.drawable.bg_login),
//        contentDescription = "login image",
//        contentScale = ContentScale.FillBounds,
//        modifier = Modifier.fillMaxSize()
//    )
    Column(
        modifier = modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text("Create Account", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(90.dp))

        SelectionContainer(Modifier.width(275.dp)) {
            Text(text = "Full Name")
        }
        OutlinedTextField(value = namaPengguna, onValueChange = {namaPengguna = it}, placeholder = { Text(text = "nama pengguna")})
        Spacer(modifier = Modifier.height(8.dp))

        SelectionContainer(Modifier.width(275.dp)) {
            Text(text = "Email")
        }
        OutlinedTextField(value = email, onValueChange = {email = it}, placeholder = { Text(text = "example@example.com")})
        Spacer(modifier = Modifier.height(8.dp))

        SelectionContainer(Modifier.width(275.dp)) {
            Text(text = "Mobile Number")
        }
        OutlinedTextField(value = mobileNumber, onValueChange = {mobileNumber = it}, placeholder = { Text(text = "+621234567890")})
        Spacer(modifier = Modifier.height(8.dp))

        SelectionContainer(Modifier.width(275.dp)) {
            Text(text = "Password")
        }
        OutlinedTextField(value = password, onValueChange = {password = it}, placeholder = { Text(text = "********")}, visualTransformation = PasswordVisualTransformation())
        Spacer(modifier = Modifier.height(8.dp))

        SelectionContainer(Modifier.width(275.dp)) {
            Text(text = "Confirm Password")
        }
        OutlinedTextField(value = confirmPassword, onValueChange = {confirmPassword = it}, placeholder = { Text(text = "********")}, visualTransformation = PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onContinueClicked,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0,208,158))
        ) {
            Text("Sign In", color = Color.Black)
        }
        Row {
            Text(text = "Already have an account?", fontSize = 12.sp, fontWeight = FontWeight.Normal)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Login", color = Color.Blue, fontSize = 12.sp, fontWeight = FontWeight.Normal, modifier = Modifier.clickable { onContinueClicked() })
//            TextButton(onClick = { /*TODO*/ }) {
//                Text(text = "Log In", fontSize = 12.sp, fontWeight = FontWeight.Normal)
//            }
        }

    }
}

@Composable
fun HomeScreen(onContinueClicked: () -> Unit,
                modifier: Modifier = Modifier) {
    var rewardPoin by rememberSaveable { mutableIntStateOf(1500) }

    Image(
        painter = painterResource(id = R.drawable.home_eas),
        contentDescription = "home eas",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(25.dp))

        Row {
            SelectionContainer(
                Modifier
                    .width(300.dp)
                    .height(95.dp)){
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text("Welcome Davian Benito", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Starbucks Greats You...", fontSize = 14.sp)
                    Divider(color = Color(223,247,226), thickness = 1.dp, modifier = Modifier
                        .width(300.dp)
                        .padding(vertical = 7.dp))

                    Row() {
                        Text(text = "Total Poin Rewards", fontSize = 14.sp, modifier = Modifier.padding(vertical = 5.dp))
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "$rewardPoin", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(201, 164, 96))
                    }
                }
            }
            Image(painter = painterResource(id = R.drawable.notif), contentDescription = "notif", modifier = Modifier
                .size(25.dp)
                .clickable {})
            Spacer(modifier = Modifier.width(5.dp))
            Image(painter = painterResource(id = R.drawable.account), contentDescription = "account", modifier = Modifier
                .size(25.dp)
                .clickable {})
        }


        Spacer(modifier = Modifier.height(50.dp))
        Image(painter = painterResource(id = R.drawable.reward_goals), contentDescription = "account", modifier = Modifier
            .size(500.dp, 80.dp))

        Button(
            onClick = onContinueClicked,
            colors = ButtonDefaults.buttonColors(containerColor = Color(58, 59, 55)),
            modifier = Modifier.size(180.dp, 40.dp)
        ) {
            Text("Collect Reward", color = Color.White, modifier = Modifier
                .clickable { rewardPoin += 700 })
        }

        Spacer(modifier = Modifier.height(45.dp))

        Row {
            Image(painter = painterResource(id = R.drawable.image_1), contentDescription = "calender", modifier = Modifier.size(50.dp))
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = "Free Any Drink", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(vertical = 1.dp))
                Text(text = "Syarat Ketentuan Berlaku", fontSize = 10.sp, fontWeight = FontWeight.Normal)
            }
            Spacer(modifier = Modifier.width(50.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Redeem", fontSize = 10.sp, fontWeight = FontWeight.Normal, modifier = Modifier.padding(vertical = 1.dp))
                Text(text = "1000 Poin", fontSize = 17.sp, fontWeight = FontWeight.Medium, modifier = Modifier
                    .clickable { rewardPoin -= 1000 })
            }
//
        }
        Spacer(modifier = Modifier.height(25.dp))

        Row {
            Image(painter = painterResource(id = R.drawable.image_2), contentDescription = "track", modifier = Modifier.size(50.dp))
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = "Diskon 50%", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(vertical = 1.dp))
                Text(text = "Syarat Ketentuan Berlaku", fontSize = 10.sp, fontWeight = FontWeight.Normal)
            }
            Spacer(modifier = Modifier.width(50.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Redeem", fontSize = 10.sp, fontWeight = FontWeight.Normal, modifier = Modifier.padding(vertical = 1.dp))
                Text(text = "700 Poin", fontSize = 17.sp, fontWeight = FontWeight.Medium, modifier = Modifier
                    .clickable { rewardPoin -= 700 })
            }
        }
        Spacer(modifier = Modifier.height(25.dp))

        Row {
            Image(painter = painterResource(id = R.drawable.image_3), contentDescription = "transcript", modifier = Modifier.size(50.dp))
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = "Free Sandwich", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(vertical = 1.dp))
                Text(text = "Syarat Ketentuan Berlaku", fontSize = 10.sp, fontWeight = FontWeight.Normal)
            }
            Spacer(modifier = Modifier.width(50.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Redeem", fontSize = 10.sp, fontWeight = FontWeight.Normal, modifier = Modifier.padding(vertical = 1.dp))
                Text(text = "1500 Poin", fontSize = 17.sp, fontWeight = FontWeight.Medium, modifier = Modifier
                    .clickable { rewardPoin -= 1500 })
            }
        }

        Row {
            Image(painter = painterResource(id = R.drawable.promo1), contentDescription = "Promo Starbucks", modifier = Modifier.size(175.dp))
            Spacer(modifier = Modifier.width(20.dp))
                Image(painter = painterResource(id = R.drawable.promo2), contentDescription = "Promo Hari2 Skripsi", modifier = Modifier.size(175.dp))
        }

    }
}

@Preview()
@Composable
fun HomePreview() {
    MyAppTheme {
        HomeScreen(onContinueClicked = {})
    }
}



//@Composable
//fun JadwalScreen(onContinueClicked: () -> Unit,
//               modifier: Modifier = Modifier) {
//
//    Image(
//        painter = painterResource(id = R.drawable.bg_main),
//        contentDescription = "login image",
//        contentScale = ContentScale.FillBounds,
//        modifier = Modifier.fillMaxSize()
//    )
//    Column(
//        modifier = modifier
//            .fillMaxSize()
//            .padding(horizontal = 20.dp),
////        verticalArrangement = Arrangement.Center,
////        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Spacer(modifier = Modifier.height(60.dp))
//
//        Row {
//            SelectionContainer(
//                Modifier
//                    .width(300.dp)
//                    .height(95.dp)){
//                Column(
//                    modifier = modifier
//                        .fillMaxSize()
//                        .padding(horizontal = 20.dp),
////        verticalArrangement = Arrangement.Center,
////        horizontalAlignment = Alignment.CenterHorizontally
//                ){
//                    Text("Welcome Davian Benito", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//                    Text(text = "Semangat ITS!!", fontSize = 14.sp)
//                    Divider(color = Color(223,247,226), thickness = 1.dp, modifier = Modifier
//                        .width(300.dp)
//                        .padding(vertical = 7.dp))
//
//                    Row() {
//                        Text(text = "5025201220", fontSize = 14.sp)
//                        Spacer(modifier = Modifier.width(20.dp))
//                        Text(text = "Teknik Informatika", fontSize = 14.sp)
//                    }
//                }
//            }
//            Image(painter = painterResource(id = R.drawable.notif), contentDescription = "notif", modifier = Modifier
//                .size(25.dp)
//                .clickable {})
//            Spacer(modifier = Modifier.width(5.dp))
//            Image(painter = painterResource(id = R.drawable.account), contentDescription = "account", modifier = Modifier
//                .size(25.dp)
//                .clickable {})
//        }
//
//
//        Spacer(modifier = Modifier.height(60.dp))
//        SelectionContainer(modifier = Modifier.fillMaxWidth()) {
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Text(text = "Jadwal Kuliah", fontSize = 20.sp, fontWeight = FontWeight.Medium)
//                Text(text = "WEEKLY", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//            }
//        }
//
//
//        Spacer(modifier = Modifier.height(60.dp))
//
//        Row {
//            Text(text = "Senin", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
//            Spacer(modifier = Modifier.width(25.dp))
//            Divider(modifier = Modifier
//                .width(1.dp)
//                .height(30.dp))
//            Spacer(modifier = Modifier.width(20.dp))
//
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Text(text = "Pemrograman Perangkat Bergerak", fontSize = 15.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(vertical = 5.dp))
//                Text(text = "13.30 - 15.20", fontSize = 12.sp, fontWeight = FontWeight.Normal)
//                Text(text = "IF 101", fontSize = 12.sp, fontWeight = FontWeight.Normal)
//            }
//        }
//        Spacer(modifier = Modifier.height(25.dp))
//
//        Divider(color = Color(223,247,226), thickness = 1.dp, modifier = Modifier
//            .width(500.dp)
//            .padding(vertical = 10.dp)
//        )
//
//        Row {
//            Text(text = "Selasa", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
//            Spacer(modifier = Modifier.width(25.dp))
//            Divider(modifier = Modifier
//                .width(1.dp)
//                .height(30.dp))
//            Spacer(modifier = Modifier.width(20.dp))
//
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Text(text = "Pemrograman Perangkat Bergerak", fontSize = 15.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(vertical = 5.dp))
//                Text(text = "13.30 - 15.20", fontSize = 12.sp, fontWeight = FontWeight.Normal)
//                Text(text = "IF 101", fontSize = 12.sp, fontWeight = FontWeight.Normal)
//            }
//        }
//        Spacer(modifier = Modifier.height(25.dp))
//
//        Divider(color = Color(223,247,226), thickness = 1.dp, modifier = Modifier
//            .width(500.dp)
//            .padding(vertical = 10.dp)
//        )
//
//
//
//    }
//}
