package com.example.tictactoe

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            FirstScreen()

        }

    }

    @Composable
    fun FirstScreen() {
        val roll = rememberNavController()
        NavHost(navController = roll, startDestination = "happy/{name1}/{name2}", builder = {
                composable("happy/{name1}/{name2}",arguments = listOf(navArgument("name1"){type = NavType.StringType},
                navArgument("name2"){type = NavType.StringType}))
                {backStackEntry->
                Starter(navController = roll,backStackEntry.arguments?.getString("name1"),backStackEntry.arguments?.getString("name2")
                )}
                composable(
                    "computer/{user1}/{user2}",
                    arguments = listOf(navArgument("user1") {type = NavType.StringType},
                        navArgument("user2"){type = NavType.StringType})
                )
                { backStackEntry ->
                    Computer(navController = roll, backStackEntry.arguments?.getString("user1")?:"",
                        backStackEntry.arguments?.getString("user2")?:""
                    )
                }
                composable(
                    "human/{name1}/{name2}",
                    arguments = listOf(navArgument("name1") { type = NavType.StringType },
                        navArgument("name2") { type = NavType.StringType })
                )
                { backStackEntry ->
                    Human(
                        navController = roll,
                        backStackEntry.arguments?.getString("name1")?:"",
                        backStackEntry.arguments?.getString("name2")?:""
                    )
                }
                composable(
                    "board1/{name1}/{name2}",
                    arguments = listOf(navArgument("name1") { type = NavType.StringType },
                        navArgument("name2"){type = NavType.StringType})

                )
                { backStackEntry ->
                    Board1(navController = roll, backStackEntry.arguments?.getString("name1")?:"",
                        backStackEntry.arguments?.getString("name2")?:"")
                }
                composable(
                    "board2/{name1}/{name2}",
                    arguments = listOf(navArgument("name1") { type = NavType.StringType },
                        navArgument("name2") { type = NavType.StringType })
                )
                { backStackEntry ->
                    Board2(
                        navController = roll, backStackEntry.arguments?.getString("name1")?:"",
                        backStackEntry.arguments?.getString("name2")?:""
                    )
                }

            })
        }

    }

    @Composable
    fun Computer(navController: NavController,name1:String,name2:String) {
        Column(modifier = Modifier.padding(5.dp, 0.dp)) {
            var comp = name1
            comp = "Kilo"
            var verify = true

            Spacer(modifier = Modifier.size(0.dp, 30.dp))

            Text(
                text = "Tic Tac Toe",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
                modifier = Modifier.align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(0.dp, 3.dp))
            Text(
                text = "In this Tic-tac-toe you are going to play",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "with a computer called $comp",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Please choose any name to play with $comp",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )

            var name by remember { mutableStateOf("") }
            Spacer(modifier = Modifier.size(20.dp))
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Name:")},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(20.dp))

            if(name.isEmpty())
                verify = false

            var holder = name2
            holder = name
            Button( enabled = verify,
                onClick = {
                    navController.navigate("board1/$comp/$holder")

                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                modifier = Modifier
                    .width(100.dp)
                    .align(CenterHorizontally)


                ) {
                Text(text = "Play", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            }
        }

    }

    @Composable
    fun Human(navController: NavController, name1: String, name2: String) {
        Column(modifier = Modifier.padding(5.dp, 0.dp)) {
            var verify = true
            Spacer(modifier = Modifier.size(0.dp, 17.dp))

            Text(
                text = "Tic Tac Toe",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
                modifier = Modifier.align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(0.dp, 3.dp))
            Text(
                text = "In this Tic-tac-toe two users will play against each other",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Users should select unique names",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Please choose any name of your choice",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.size(10.dp))

            var player1 by remember { mutableStateOf("") }
            var player2 by remember { mutableStateOf("") }

            TextField(
                value = player1,
                onValueChange = { player1 = it },
                label = { Text(text = "Player1:", fontSize = 15.sp) },
                textStyle = TextStyle(fontSize = 25.sp),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.size(12.dp))

            TextField(
                value = player2,
                onValueChange = { player2 = it },
                label = { Text(text = "Player2:", fontSize = 15.sp) },
                textStyle = TextStyle(fontSize = 25.sp),
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(5.dp))
            if(player1.isEmpty() || player2.isEmpty())
                verify = false

            var holder1 = name1
            holder1 = player1
            var holder2 = name2
            holder2 = player2


            Button(enabled = verify,
                onClick = {
                    navController.navigate("board2/$holder1/$holder2")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                modifier = Modifier
                    .width(100.dp)
                    .align(CenterHorizontally)
            )
            {
                Text(text = "Play", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            }
        }
    }

    @Composable
    fun Starter(navController: NavController,name1:String?,name2: String?) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(0.dp, 80.dp))

            Text(
                text = "Tic-Tac-Toe",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )

            Text(text = "also know as", fontWeight = FontWeight.Medium, fontSize = 25.sp)

            Text(text = "Gambian Flag", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
            Image(
                painter = painterResource(id = R.drawable.tictac), modifier = Modifier
                    .size(200.dp, 200.dp)
                    .align(alignment = CenterHorizontally), contentDescription = "hello"
            )
            Spacer(modifier = Modifier.size(0.dp, 40.dp))

            Text(
                text = "Would you like to play with",
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.size(0.dp, 20.dp))
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = {
                        navController.navigate("computer/hello/hello")

                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                    modifier = Modifier.width(115.dp)
                ) {
                    Text(text = "Computer", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.size(10.dp, 0.dp))
                Button(
                    onClick = {
                        navController.navigate("human/hello/hello")

                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                    modifier = Modifier.width(115.dp)
                ) {
                    Text(text = "Human", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }

            }
        }
    }

    @Composable
    fun Board1(navController: NavController,name1:String, name2: String) {

        val board = arrayOf(
            charArrayOf(' ', ' ', ' '),
            charArrayOf(' ', ' ', ' '),
            charArrayOf(' ', ' ', ' '),
            charArrayOf(' ', ' ', ' '),
            charArrayOf(' ', ' ', ' ')
        )

        var bt1 by remember { mutableStateOf(board[0][0]) }
        var bt2 by remember { mutableStateOf(board[0][1]) }
        var bt3 by remember { mutableStateOf(board[0][2]) }
        var bt4 by remember { mutableStateOf(board[1][0]) }
        var bt5 by remember { mutableStateOf(board[1][1]) }
        var bt6 by remember { mutableStateOf(board[1][2]) }
        var bt7 by remember { mutableStateOf(board[2][0]) }
        var bt8 by remember { mutableStateOf(board[2][1]) }
        var bt9 by remember { mutableStateOf(board[2][2]) }


        var winner by remember { mutableStateOf("") }
        var screen by remember { mutableStateOf(name1) }


        Column(modifier = Modifier.padding(5.dp)) {

            Text(
                text = "Tic Tac Toe",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(4.dp))

            var status by remember { mutableStateOf("") }
            Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {
                Text(text = "computer: $name1")
                Spacer(modifier = Modifier.size(15.dp))
                Text(text = "user: $name2")
            }
            status = name1
            Spacer(modifier = Modifier.size(15.dp))
            TextField(
                value = "Turn: $status",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(), enabled = false
            )
            Spacer(modifier = Modifier.size(15.dp))
            Column(modifier = Modifier.padding(5.dp)) {
                var com by remember { mutableStateOf(true) }

                if (winner == name1 || winner == name2) {
                    com = false
                }

                Text(
                    text = "Tic Tac Toe",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.align(CenterHorizontally)
                )
                Spacer(modifier = Modifier.size(4.dp))

                Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Player1:$name1")
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(text = "Player2:$name2")
                }

                Spacer(modifier = Modifier.size(15.dp))
                TextField(
                    value = "Turn: $screen",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(), enabled = false
                )
                Spacer(modifier = Modifier.size(15.dp))
                Column(modifier = Modifier.border(5.dp, Color.Black)) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Button(
                            enabled = com,
                            onClick = {
                                if (bt1 == ' ') {
                                    if (screen == name1) {
                                        bt1 = 'X'
                                        board[0][0] = 'X'
                                        screen = name2
                                    } else if (screen == name2) {
                                        bt1 = 'O'
                                        board[0][0] = 'O'
                                        screen = name1
                                    }
                                }

                            },
                            modifier = Modifier.size(110.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                        ) {
                            if (bt1 == 'X') {
                                Text(
                                    text = "X",
                                    color = Color.Green,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp
                                )
                            } else if (bt1 == 'O') {
                                Text(
                                    text = "O",
                                    color = Color.Red,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp
                                )
                            }
                        }

                        Button(
                            enabled = com,
                            onClick = {
                                if (bt2 == ' ') {
                                    if (screen == name1) {
                                        bt2 = 'X'
                                        board[0][1] = 'X'
                                        screen = name2
                                    } else if (screen == name2) {
                                        bt2 = 'O'
                                        board[0][1] = 'O'
                                        screen = name1
                                    }
                                }
                            },
                            modifier = Modifier.size(110.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                        ) {
                            if (bt2 == 'X') {
                                Text(
                                    text = "X",
                                    color = Color.Green,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp
                                )
                            } else if (bt2 == 'O') {
                                Text(
                                    text = "O",
                                    color = Color.Red,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp
                                )
                            }
                        }
                        Button(
                            enabled = com,
                            onClick = {
                                if (bt3 == ' ') {
                                    if (screen == name1) {
                                        bt3 = 'X'
                                        board[0][2] = 'X'
                                        screen = name2
                                    } else if (screen == name2) {
                                        bt3 = 'O'
                                        board[0][2] = 'O'
                                        screen = name1
                                    }
                                }
                            },
                            modifier = Modifier.size(110.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                        ) {
                            if (bt3 == 'X') {
                                Text(
                                    text = "X",
                                    color = Color.Green,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp
                                )
                            } else if (bt3 == 'O') {
                                Text(
                                    text = "O",
                                    color = Color.Red,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.size(4.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            enabled = com,
                            onClick = {
                                if (bt4 == ' ') {
                                    if (screen == name1) {
                                        bt4 = 'X'
                                        board[1][0] = 'X'
                                        screen = name2
                                    } else if (screen == name2) {
                                        bt4 = 'O'
                                        board[1][0] = 'O'
                                        screen = name1
                                    }
                                }
                            },
                            modifier = Modifier.size(110.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                        ) {
                            if (bt4 == 'X') {
                                Text(
                                    text = "X",
                                    color = Color.Green,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp
                                )
                            } else if (bt4 == 'O') {
                                Text(
                                    text = "O",
                                    color = Color.Red,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp
                                )
                            }
                        }
                        Button(
                            enabled = com,
                            onClick = {
                                if (bt5 == ' ') {
                                    if (screen == name1) {
                                        bt5 = 'X'
                                        board[1][1] = 'X'
                                        screen = name2
                                    } else if (screen == name2) {
                                        bt5 = 'O'
                                        board[1][1] = 'O'
                                        screen = name1
                                    }
                                }
                            },
                            modifier = Modifier.size(110.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                        ) {
                            if (bt5 == 'X') {
                                Text(
                                    text = "X",
                                    color = Color.Green,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp
                                )
                            } else if (bt5 == 'O') {
                                Text(
                                    text = "O",
                                    color = Color.Red,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp
                                )
                            }
                        }

                        Button(
                            enabled = com,
                            onClick = {
                                if (bt6 == ' ') {
                                    if (screen == name1) {
                                        bt6 = 'X'
                                        board[1][2] = 'X'
                                        screen = name2
                                    } else if (screen == name2) {
                                        bt6 = 'O'
                                        board[1][2] = 'O'
                                        screen = name1
                                    }
                                }
                            },
                            modifier = Modifier.size(110.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                        ) {
                            if (bt6 == 'X') {
                                Text(
                                    text = "X",
                                    color = Color.Green,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp
                                )
                            } else if (bt6 == 'O') {
                                Text(
                                    text = "O",
                                    color = Color.Red,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.size(4.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            enabled = com,
                            onClick = {
                                if (bt7 == ' ') {
                                    if (screen == name1) {
                                        bt7 = 'X'
                                        board[2][0] = 'X'
                                        screen = name2
                                    } else if (screen == name2) {
                                        bt7 = 'O'
                                        board[2][0] = 'O'
                                        screen = name1
                                    }
                                }
                            },
                            modifier = Modifier.size(110.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                        ) {
                            if (bt7 == 'X') {
                                Text(
                                    text = "X",
                                    color = Color.Green,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp
                                )
                            } else if (bt7 == 'O') {
                                Text(
                                    text = "O",
                                    color = Color.Red,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp
                                )
                            }
                        }
                        Button(
                            enabled = com,
                            onClick = {
                                if (bt8 == ' ') {
                                    if (screen == name1) {
                                        bt8 = 'X'
                                        board[2][1] = 'X'
                                        screen = name2
                                    } else if (screen == name2) {
                                        bt8 = 'O'
                                        board[2][1] = 'O'
                                        screen = name1
                                    }
                                }
                            },
                            modifier = Modifier.size(110.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                        ) {
                            if (bt8 == 'X') {
                                Text(
                                    text = "X",
                                    color = Color.Green,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp
                                )
                            } else if (bt8 == 'O') {
                                Text(
                                    text = "O",
                                    color = Color.Red,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp
                                )
                            }
                        }

                        Button(
                            enabled = com,
                            onClick = {
                                if (bt9 == ' ') {
                                    if (screen == name1) {
                                        bt9 = 'X'
                                        board[2][2] = 'X'
                                        screen = name2
                                    } else if (screen == name2) {
                                        bt9 = 'O'
                                        board[2][2] = 'O'
                                        screen = name1
                                    }
                                }
                            },
                            modifier = Modifier.size(110.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                        ) {
                            if (bt9 == 'X') {
                                Text(
                                    text = "X",
                                    color = Color.Green,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp
                                )
                            } else if (bt9 == 'O') {
                                Text(
                                    text = "O",
                                    color = Color.Red,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Spacer(modifier = Modifier.size(20.dp))
                if (
                //checking the rows for the winner
                    bt1 == 'X' && bt2 == 'X' && bt3 == 'X' ||
                    bt4 == 'X' && bt5 == 'X' && bt6 == 'X' ||
                    bt7 == 'X' && bt8 == 'X' && bt9 == 'X' ||
                    //checking the columns for the winner
                    bt1 == 'X' && bt4 == 'X' && bt7 == 'X' ||
                    bt2 == 'X' && bt5 == 'X' && bt8 == 'X' ||
                    bt3 == 'X' && bt6 == 'X' && bt9 == 'X' ||

                    //checking for diagonols
                    bt1 == 'X' && bt5 == 'X' && bt9 == 'X' ||
                    bt7 == 'X' && bt5 == 'X' && bt3 == 'X'

                ) {
                    winner = name1

                }

                if (
                //checking the rows for the winner
                    bt1 == 'O' && bt2 == 'O' && bt3 == 'O' ||
                    bt4 == 'O' && bt5 == 'O' && bt6 == 'O' ||
                    bt7 == 'O' && bt8 == 'O' && bt9 == 'O' ||
                    //checking the columns for the winner
                    bt1 == 'O' && bt4 == 'O' && bt7 == 'O' ||
                    bt2 == 'O' && bt5 == 'O' && bt8 == 'O' ||
                    bt3 == 'O' && bt6 == 'O' && bt9 == 'O' ||

                    //checking for diagonols
                    bt1 == 'O' && bt5 == 'O' && bt9 == 'O' ||
                    bt7 == 'O' && bt5 == 'O' && bt3 == 'O'

                ) {
                    winner = name2

                }

                TextField(
                    value = "Winner:   $winner",
                    onValueChange = {},
                    //label = { Text(text = "winner:", fontWeight = FontWeight.Bold) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false
                )
                Spacer(modifier = Modifier.size(20.dp))
                Button(
                    onClick = {
                        bt1 = ' '
                        bt2 = ' '
                        bt3 = ' '
                        bt4 = ' '
                        bt5 = ' '
                        bt6 = ' '
                        bt7 = ' '
                        bt8 = ' '
                        bt9 = ' '

                        screen = name1
                        winner = " "
                        com = true

                    },
                    modifier = Modifier.align(CenterHorizontally)
                )
                {
                    Text(text = "Restart")
                }

            }
        }

        TextField(
                value = winner,
                onValueChange = {},
                label = { Text(text = "winner:", fontWeight = FontWeight.Bold) },
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )
            Spacer(modifier = Modifier.size(20.dp))
    }

    @Composable
    fun Board2(navController: NavController, name1: String, name2: String) {
        val board = arrayOf(
            charArrayOf(' ', ' ', ' '),
            charArrayOf(' ', ' ', ' '),
            charArrayOf(' ', ' ', ' '),
            charArrayOf(' ', ' ', ' '),
            charArrayOf(' ', ' ', ' ')
        )

        var bt1 by remember { mutableStateOf(board[0][0]) }
        var bt2 by remember { mutableStateOf(board[0][1]) }
        var bt3 by remember { mutableStateOf(board[0][2]) }
        var bt4 by remember { mutableStateOf(board[1][0]) }
        var bt5 by remember { mutableStateOf(board[1][1]) }
        var bt6 by remember { mutableStateOf(board[1][2]) }
        var bt7 by remember { mutableStateOf(board[2][0]) }
        var bt8 by remember { mutableStateOf(board[2][1]) }
        var bt9 by remember { mutableStateOf(board[2][2]) }


        var winner by remember { mutableStateOf("") }
        var screen by remember { mutableStateOf(name1) }


        Column(modifier = Modifier.padding(5.dp)) {
            var com by remember { mutableStateOf(true) }

            if (winner == name1 || winner == name2) {
                com = false
            }

            Text(
                text = "Tic Tac Toe",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(4.dp))

            Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Player1:$name1")
                Spacer(modifier = Modifier.size(15.dp))
                Text(text = "Player2:$name2")
            }

            Spacer(modifier = Modifier.size(15.dp))
            TextField(
                value = "Turn: $screen",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(), enabled = false
            )
            Spacer(modifier = Modifier.size(15.dp))
            Column(modifier = Modifier.border(5.dp, Color.Black)) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Button(
                        enabled = com,
                        onClick = {
                            if (bt1 == ' ') {
                                if (screen == name1) {
                                    bt1 = 'X'
                                    board[0][0] = 'X'
                                    screen = name2
                                } else if (screen == name2) {
                                    bt1 = 'O'
                                    board[0][0] = 'O'
                                    screen = name1
                                }
                            }

                        },
                        modifier = Modifier.size(110.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                    ) {
                        if (bt1 == 'X') {
                            Text(
                                text = "X",
                                color = Color.Green,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        } else if (bt1 == 'O') {
                            Text(
                                text = "O",
                                color = Color.Red,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        }
                    }

                    Button(
                        enabled = com,
                        onClick = {
                            if (bt2 == ' ') {
                                if (screen == name1) {
                                    bt2 = 'X'
                                    board[0][1] = 'X'
                                    screen = name2
                                } else if (screen == name2) {
                                    bt2 = 'O'
                                    board[0][1] = 'O'
                                    screen = name1
                                }
                            }
                        },
                        modifier = Modifier.size(110.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                    ) {
                        if (bt2 == 'X') {
                            Text(
                                text = "X",
                                color = Color.Green,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        } else if (bt2 == 'O') {
                            Text(
                                text = "O",
                                color = Color.Red,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        }
                    }
                    Button(
                        enabled = com,
                        onClick = {
                            if (bt3 == ' ') {
                                if (screen == name1) {
                                    bt3 = 'X'
                                    board[0][2] = 'X'
                                    screen = name2
                                } else if (screen == name2) {
                                    bt3 = 'O'
                                    board[0][2] = 'O'
                                    screen = name1
                                }
                            }
                        },
                        modifier = Modifier.size(110.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                    ) {
                        if (bt3 == 'X') {
                            Text(
                                text = "X",
                                color = Color.Green,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        } else if (bt3 == 'O') {
                            Text(
                                text = "O",
                                color = Color.Red,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.size(4.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        enabled = com,
                        onClick = {
                            if (bt4 == ' ') {
                                if (screen == name1) {
                                    bt4 = 'X'
                                    board[1][0] = 'X'
                                    screen = name2
                                } else if (screen == name2) {
                                    bt4 = 'O'
                                    board[1][0] = 'O'
                                    screen = name1
                                }
                            }
                        },
                        modifier = Modifier.size(110.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                    ) {
                        if (bt4 == 'X') {
                            Text(
                                text = "X",
                                color = Color.Green,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        } else if (bt4 == 'O') {
                            Text(
                                text = "O",
                                color = Color.Red,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        }
                    }
                    Button(
                        enabled = com,
                        onClick = {
                            if (bt5 == ' ') {
                                if (screen == name1) {
                                    bt5 = 'X'
                                    board[1][1] = 'X'
                                    screen = name2
                                } else if (screen == name2) {
                                    bt5 = 'O'
                                    board[1][1] = 'O'
                                    screen = name1
                                }
                            }
                        },
                        modifier = Modifier.size(110.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                    ) {
                        if (bt5 == 'X') {
                            Text(
                                text = "X",
                                color = Color.Green,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        } else if (bt5 == 'O') {
                            Text(
                                text = "O",
                                color = Color.Red,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        }
                    }

                    Button(
                        enabled = com,
                        onClick = {
                            if (bt6 == ' ') {
                                if (screen == name1) {
                                    bt6 = 'X'
                                    board[1][2] = 'X'
                                    screen = name2
                                } else if (screen == name2) {
                                    bt6 = 'O'
                                    board[1][2] = 'O'
                                    screen = name1
                                }
                            }
                        },
                        modifier = Modifier.size(110.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                    ) {
                        if (bt6 == 'X') {
                            Text(
                                text = "X",
                                color = Color.Green,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        } else if (bt6 == 'O') {
                            Text(
                                text = "O",
                                color = Color.Red,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.size(4.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        enabled = com,
                        onClick = {
                            if (bt7 == ' ') {
                                if (screen == name1) {
                                    bt7 = 'X'
                                    board[2][0] = 'X'
                                    screen = name2
                                } else if (screen == name2) {
                                    bt7 = 'O'
                                    board[2][0] = 'O'
                                    screen = name1
                                }
                            }
                        },
                        modifier = Modifier.size(110.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                    ) {
                        if (bt7 == 'X') {
                            Text(
                                text = "X",
                                color = Color.Green,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        } else if (bt7 == 'O') {
                            Text(
                                text = "O",
                                color = Color.Red,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        }
                    }
                    Button(
                        enabled = com,
                        onClick = {
                            if (bt8 == ' ') {
                                if (screen == name1) {
                                    bt8 = 'X'
                                    board[2][1] = 'X'
                                    screen = name2
                                } else if (screen == name2) {
                                    bt8 = 'O'
                                    board[2][1] = 'O'
                                    screen = name1
                                }
                            }
                        },
                        modifier = Modifier.size(110.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                    ) {
                        if (bt8 == 'X') {
                            Text(
                                text = "X",
                                color = Color.Green,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        } else if (bt8 == 'O') {
                            Text(
                                text = "O",
                                color = Color.Red,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        }
                    }

                    Button(
                        enabled = com,
                        onClick = {
                            if (bt9 == ' ') {
                                if (screen == name1) {
                                    bt9 = 'X'
                                    board[2][2] = 'X'
                                    screen = name2
                                } else if (screen == name2) {
                                    bt9 = 'O'
                                    board[2][2] = 'O'
                                    screen = name1
                                }
                            }
                        },
                        modifier = Modifier.size(110.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                    ) {
                        if (bt9 == 'X') {
                            Text(
                                text = "X",
                                color = Color.Green,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        } else if (bt9 == 'O') {
                            Text(
                                text = "O",
                                color = Color.Red,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
            }
            Spacer(modifier = Modifier.size(20.dp))
                if (
                //checking the rows for the winner
                    bt1 == 'X' && bt2 == 'X' && bt3 == 'X' ||
                    bt4 == 'X' && bt5 == 'X' && bt6 == 'X' ||
                    bt7 == 'X' && bt8 == 'X' && bt9 == 'X' ||
                    //checking the columns for the winner
                    bt1 == 'X' && bt4 == 'X' && bt7 == 'X' ||
                    bt2 == 'X' && bt5 == 'X' && bt8 == 'X' ||
                    bt3 == 'X' && bt6 == 'X' && bt9 == 'X' ||

                    //checking for diagonols
                    bt1 == 'X' && bt5 == 'X' && bt9 == 'X' ||
                    bt7 == 'X' && bt5 == 'X' && bt3 == 'X'

                ) {
                    winner = name1

                }

                if (
                //checking the rows for the winner
                    bt1 == 'O' && bt2 == 'O' && bt3 == 'O' ||
                    bt4 == 'O' && bt5 == 'O' && bt6 == 'O' ||
                    bt7 == 'O' && bt8 == 'O' && bt9 == 'O' ||
                    //checking the columns for the winner
                    bt1 == 'O' && bt4 == 'O' && bt7 == 'O' ||
                    bt2 == 'O' && bt5 == 'O' && bt8 == 'O' ||
                    bt3 == 'O' && bt6 == 'O' && bt9 == 'O' ||

                    //checking for diagonols
                    bt1 == 'O' && bt5 == 'O' && bt9 == 'O' ||
                    bt7 == 'O' && bt5 == 'O' && bt3 == 'O'

                ) {
                    winner = name2

                }

                TextField(
                    value = "Winner:   $winner",
                    onValueChange = {},
                    //label = { Text(text = "winner:", fontWeight = FontWeight.Bold) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false
                )
                Spacer(modifier = Modifier.size(20.dp))
                Button(
                    onClick = {
                        bt1 = ' '
                        bt2 = ' '
                        bt3 = ' '
                        bt4 = ' '
                        bt5 = ' '
                        bt6 = ' '
                        bt7 = ' '
                        bt8 = ' '
                        bt9 = ' '

                        screen = name1
                        winner = " "
                        com = true

                    },
                    modifier = Modifier.align(CenterHorizontally)
                )
                {
                    Text(text = "Restart")
                }



        }
    }









