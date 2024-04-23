package com.nncstudio.allclass

import android.graphics.drawable.Icon
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toDrawable
import com.nncstudio.allclass.data.DataSource
import com.nncstudio.allclass.model.Topic
import com.nncstudio.allclass.ui.theme.AllClassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AllClassTheme {
                // A surface container using the 'background' color from the theme
                FullApp()
                }
            }
        }
    }



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    var search by remember {
        mutableStateOf("")
    }
    TextField(
        value = search,
        onValueChange ={ search = it },
        leadingIcon = {
                      Icon(Icons.Default.Search, contentDescription = null)
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        placeholder = {
                      Text(stringResource(R.string.search))
        },
        modifier = modifier
            .heightIn(min = 56.dp)
            .fillMaxWidth()
    )

}

@Preview(showBackground = true,backgroundColor = 0xFFF5F0EE)
@Composable
private fun SearchBarPreview() {
    AllClassTheme {
        SearchBar(

            Modifier.padding(8.dp))
    }

}


@Composable
fun HomeSection(
   @StringRes title:Int,
    modifier: Modifier = Modifier,
   content: @Composable ()-> Unit
) {

    Column(modifier = modifier) {
        Text(stringResource(id = title),
            modifier = Modifier
                .paddingFromBaseline(
                    top = 40.dp,
                    bottom = 30.dp
                )
                .padding(horizontal = 16.dp)
        )
        content()

    }

}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Column(

        modifier = Modifier
            .padding(vertical = 16.dp)
            .safeDrawingPadding()
    ) {

        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }

        HomeSection(title = R.string.favarite_collections) {
            FavoriteCollectionGrid()

        }

    }

}


@Preview(showBackground = true,backgroundColor = 0xFFF5F0EE)
@Composable
private fun ScreenContent() {
    HomeScreen()

}


@Preview(showBackground = true,backgroundColor = 0xFFF5F0EE)
@Composable
private fun HomeSectionPreview() {
    AllClassTheme {
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
    
}

private val alignBodyData = listOf(
    R.drawable.architecture to R.string.architecture,
    R.drawable.crafts to R.string.crafts,
    R.drawable.culinary to R.string.culinary,
    R.drawable.design to R.string.design,
    R.drawable.architecture to R.string.architecture,
    R.drawable.crafts to R.string.crafts,
    R.drawable.culinary to R.string.culinary,
    R.drawable.design to R.string.design,
    R.drawable.architecture to R.string.architecture,
    R.drawable.crafts to R.string.crafts,
    R.drawable.culinary to R.string.culinary,
    R.drawable.design to R.string.design,
    R.drawable.architecture to R.string.architecture,
    R.drawable.crafts to R.string.crafts,
    R.drawable.culinary to R.string.culinary,
    R.drawable.design to R.string.design,
    R.drawable.business to R.string.business
).map { DrawableStringPair(it.first,it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {

    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier

    ){
        items(alignBodyData) {item->
            AlignBodyElement(
                drawable = item.drawable,
                text = item.text
            )

        }

    }

}


@Composable
fun AlignBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text:Int,
    modifier: Modifier = Modifier
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier) {
      Image(
          painter = painterResource(drawable),
          contentDescription = null,
          contentScale = ContentScale.Crop,
          modifier = Modifier
              .size(88.dp)
              .clip(CircleShape)
      )
        Text(text = stringResource(text),
            modifier = Modifier
                .paddingFromBaseline(top = 24.dp, bottom = 8.dp)
            )

    }

}


@Preview(showBackground = true,backgroundColor = 0xFFF5F0EE)
@Composable
private fun  AlignBodyElementPreview() {
    AllClassTheme {
      AlignYourBodyRow()
    }

}


private val  FavoriteCollectionCardData = listOf(
    R.drawable.architecture to R.string.architecture,
    R.drawable.crafts to R.string.crafts,
    R.drawable.culinary to R.string.culinary,
    R.drawable.design to R.string.design,
    R.drawable.architecture to R.string.architecture,
    R.drawable.crafts to R.string.crafts,
    R.drawable.culinary to R.string.culinary,
    R.drawable.design to R.string.design,
    R.drawable.architecture to R.string.architecture,
    R.drawable.crafts to R.string.crafts,
    R.drawable.culinary to R.string.culinary,
    R.drawable.design to R.string.design,
    R.drawable.architecture to R.string.architecture,
    R.drawable.crafts to R.string.crafts,
    R.drawable.culinary to R.string.culinary,
    R.drawable.design to R.string.design,
    R.drawable.architecture to R.string.architecture,
    R.drawable.crafts to R.string.crafts,
    R.drawable.culinary to R.string.culinary,
    R.drawable.design to R.string.design,
    R.drawable.design to R.string.design,
    R.drawable.business to R.string.business
).map { DrawableStringPair(it.first,it.second) }


@Composable
fun FavoriteCollectionGrid(
    modifier: Modifier = Modifier
) {

    LazyHorizontalGrid(rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp,),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier
            .height(120.dp)

    ) {
        items(FavoriteCollectionCardData) {item ->
            FavoriteCollectionCard(
                drawable = item.drawable,
                text = item.text
            )
        }

    }

}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable:Int,
    @StringRes text : Int,
    modifier: Modifier = Modifier
) {
    Surface (
        shape = MaterialTheme.shapes.small,
        modifier= modifier
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier=Modifier
                .width(192.dp),
        ){
           Image(
               painter = painterResource(drawable),
               contentDescription =null,
               contentScale = ContentScale.Crop,
               modifier = Modifier.size(56.dp)
           )
            Text(stringResource(text),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                )
        }
    }

}


@Preview(showBackground = true,backgroundColor = 0xFFF5F0EE)
@Composable
private fun FavoriteCollectionCardPreview () {
    AllClassTheme {

           FavoriteCollectionGrid(
               modifier = Modifier.padding(8.dp)
           )

    }

}


@Composable
fun SoothBottomNavigation(modifier: Modifier = Modifier) {

    NavigationBar (
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ){
        NavigationBarItem(
            selected =true,
            onClick = {

            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(R.string.Home))
            }
        )

        NavigationBarItem(
            selected =false,
            onClick = {

            },
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(R.string.Account))
            }
        )

    }


}


@Composable
fun FullApp() {

    Scaffold (
        bottomBar = { SoothBottomNavigation()}
    ){ padding ->

        HomeScreen()

    }

}
@Preview(showBackground = true,backgroundColor = 0xFFF5F0EE)
@Composable
fun FullAppPreview() {
    AllClassTheme {
        FullApp()
    }
}