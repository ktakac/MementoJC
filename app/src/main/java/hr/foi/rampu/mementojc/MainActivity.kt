package hr.foi.rampu.mementojc

import TaskCard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hr.foi.rampu.mementojc.ui.theme.MementoJCTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import hr.foi.rampu.mementojc.entities.Task
import hr.foi.rampu.mementojc.helpers.MockDataLoader

@OptIn(ExperimentalFoundationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MementoJCTheme {
                tabRow()
            }
        }
    }


    @Composable
    fun tabRow() {
        var selectedTabIndex by remember {
            mutableIntStateOf(0)
        }

        val tabItem = listOf(
            TabItem(
                title = stringResource(R.string.tasks_pending),
                unSelectedItem = ImageVector.vectorResource(id = R.drawable.outline_assignment_late_24),
                selectedIcon = ImageVector.vectorResource(id = R.drawable.outline_assignment_late_24)
            ), TabItem(
                title = stringResource(R.string.tasks_completed),
                unSelectedItem = ImageVector.vectorResource(id = R.drawable.outline_assignment_turned_in_24),
                selectedIcon = ImageVector.vectorResource(id = R.drawable.baseline_assignment_turned_in_24)
            ), TabItem(
                title = stringResource(R.string.news),
                unSelectedItem = ImageVector.vectorResource(id = R.drawable.outline_article_24),
                selectedIcon = ImageVector.vectorResource(id = R.drawable.baseline_article_24)
            )
        )

        val tasksPending = MockDataLoader.getDemoData()
        val tasks = listOf(tasksPending, emptyList<Task>(), emptyList<Task>())

        val pagerState = rememberPagerState {
            tabItem.size
        }
        LaunchedEffect(key1 = selectedTabIndex) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }
        LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
            if (!pagerState.isScrollInProgress)
                selectedTabIndex = pagerState.currentPage
        }


        Column(modifier = Modifier.fillMaxSize()) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabItem.forEachIndexed { index, tabItem ->

                    androidx.compose.material3.Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
                        },
                        text = { Text(text = tabItem.title) },
                        icon = {
                            androidx.compose.material3.Icon(
                                imageVector = if (index == selectedTabIndex) {
                                    tabItem.selectedIcon
                                } else tabItem.unSelectedItem,
                                contentDescription = tabItem.title
                            )
                        }

                    )
                }
            }
            HorizontalPager(
                state = pagerState, modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { index ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopEnd
                ) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(tasks[index]) { task ->
                            TaskCard(task)
                        }
                    }
                }
            }


        }
    }
}

data class TabItem(
    val title: String, val unSelectedItem: ImageVector, val selectedIcon: ImageVector
)



