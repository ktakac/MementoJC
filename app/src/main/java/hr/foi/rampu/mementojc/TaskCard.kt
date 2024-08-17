import android.view.Surface
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import hr.foi.rampu.mementojc.entities.Task
import hr.foi.rampu.mementojc.entities.TaskCategory
import java.text.SimpleDateFormat
import java.time.format.TextStyle
import java.util.Date
import java.util.Locale

@Composable
fun TaskCard(task: Task) {
    val sdf: SimpleDateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH)
    Card(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .padding(3.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().height(IntrinsicSize.Min)
        ) {
            Box(modifier = Modifier.width(15.dp).fillMaxHeight()){
                Surface(
                    modifier = Modifier.matchParentSize(),
                    color =  Color(task.category.color.toColorInt())
                ){}
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()  // match_parent for width
                    .padding(10.dp)  // padding inside the column
            ) {
                Text(
                    text = task.name,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = sdf.format(task.dueDate),
                    style = MaterialTheme.typography.bodySmall
                )
            }

        }
    }
}
