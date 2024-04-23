package com.nncstudio.allclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nncstudio.allclass.ui.theme.AllClassTheme

class TempCalculator : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AllClassTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipApp()
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun TipApp() {

    var amountInput by remember {
        mutableStateOf("")
    }

    var tipInput by remember {
        mutableStateOf("")
    }




    val celsius = amountInput.toFloatOrNull() ?: 0f
    val fharenheit = tipInput.toFloatOrNull() ?: 0f

    val fharenheitToCel = FharenheitToCelsius(fharenheit)


    val celsiusTofhar = CelsiusToFahrenheit(celsius)



    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .safeDrawingPadding()
    ) {

        Text(text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        EditTextFild(
            labal =R.string.bill_amount,
            leadingIcon =R.drawable.baseline_attach_money_24,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = amountInput,
            onValueChange = {amountInput = it},
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        Text(text = stringResource(R.string.tip_amount,celsiusTofhar),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(20.dp))


        EditTextFild(
            labal = R.string.Fharenheit,
            leadingIcon =R.drawable.baseline_percent_24,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            value = tipInput,
            onValueChange = {tipInput = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
        )

        Text(text = stringResource(R.string.celsius,fharenheitToCel),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(20.dp))




    }

}


@Composable
fun EditTextFild(
    @StringRes labal: Int,
    @DrawableRes leadingIcon : Int,
    keyboardOptions: KeyboardOptions,
    value:String,
    onValueChange:(String) -> Unit,
    modifier: Modifier = Modifier


) {

    TextField(
        value = value,
        leadingIcon = { Icon(painter = painterResource(leadingIcon), contentDescription = null) },
        onValueChange = onValueChange,
        singleLine = true,
        modifier = modifier,
        label =  { Text(text = stringResource(labal))},
        keyboardOptions = keyboardOptions
    )

}


private fun FharenheitToCelsius(
    fharenheit: Float,

    ): Float {

    return (fharenheit - 32) * 5/9

}


private fun CelsiusToFahrenheit(
    celsius: Float,

    ): Float {

    return (celsius  * 9/5 ) + 32

}

