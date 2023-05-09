/*//google chart javascript
/*****************************
var token = [[${session.accesstoken}]] '';
google.charts.load("current", { packages: ["corechart", "bar"] });
google.charts.setOnLoadCallback(drawMultSeries);

var whitneyCounter = {easy: 0, hard: 0, moderate: 0};
var ericCounter = {easy: 0, hard: 0, moderate: 0};
var emilyCounter = {easy: 0, hard: 0, moderate: 0};

function drawMultSeries() {
  var data = new google.visualization.DataTable();
  data.addColumn("string", "Assigned To");
  data.addColumn("number", "Easy");
  data.addColumn("number", "Hard");
  data.addColumn("number", "Moderate");

  data.addRows([
    ["Whitney",whitneyCounter.easy,whitneyCounter.hard,whitneyCounter.moderate],
    ["Eric", ericCounter.easy,ericCounter.hard,ericCounter.moderate],
    ["Emily", emilyCounter.easy,emilyCounter.hard,emilyCounter.moderate]
  ]);

  var options = {
    title: "Number of Tasks and Level of Difficulty",
    hAxis: {
      title: "Assigned To"
    },
    vAxis: {
      title: "Number of Tasks (scale of 1-10)",
      minValue: 0,
      ticks: [0, 1, 2, 3, 4, 5]
    }, 
  };

  var chart = new google.visualization.ColumnChart(
    document.getElementById("chart_div")
  );
  chart.draw(data, options);
}
/*****************************
//end google chart javascript


//submits form when you click "enter"
$('#taskForm textarea').keydown(function(e) {
    if (e.keyCode == 13) {
         addTask();
    }
});


/*****************************
//function that adds the task to the page on form submit
function addTask() {
  event.preventDefault();
  
  var form = document.querySelector("form");
  var description = form.description.value;
  var assignedTo = document.querySelector('input[name="assignto"]:checked').value;
  var difficulty = document.getElementById("difficulty").value;

  var url = "/create-job?employeeId=123&employeeName=John&jobName=Example&description=" + description + "&password=12345";
  url += "&assignedTo=" + assignedTo + "&difficulty=" + difficulty;
  
  fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },

      'Authorization': token
    }
  )
  .then(response => {
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    return response.json();
  })
  .then(data => {
    console.log(data);
  })
  .catch(error => {
    console.error("There was a problem with the fetch operation:", error);
  });
}


//helps makes the form responsive
/*****************************
$(window).resize(function(){
	  	drawMultSeries();
	});



//Variable array for tasks
/*****************************
var task1 = {
  assignto: "emily",
  difficulty: "hard",
  description: "Look at doggy videos"
};

var taskList = {
  tasks: [task1]
};

//function that appends the elements to the the page 
/*****************************
function drawList() {
  var listCont = document.getElementById("list-container");
  var list = document.createElement("ul");
  for (var i = 0; i < taskList.tasks.length; i++) {
    var d = document.getElementById("difficulty");
    var item = document.createElement("li");
    var item2 = document.createElement("li");
    var item3 = document.createElement("li");
    item.innerHTML = document.querySelector('input[name="assignto"]:checked').value;
    item2.innerHTML = d.options[d.selectedIndex].value;
    item3.innerHTML = "<b>Task:</b> " + taskList.tasks[i].description;
  }
    list.appendChild(item).setAttribute("class", "person");
    list.appendChild(item2).setAttribute("class", "task-diff");
    list.appendChild(item3).setAttribute("class", "task-descrip");
    listCont.appendChild(list);
    changeColor();
  
}

window.onload = function() {
  drawList();
  var form = document.querySelector("form");
  form.onSubmit = addTask;
};

//function changes the css background color depending 
// on the inner html being easy, moderate, or hard
/*****************************
function changeColor() {
	$("li.task-diff").each(function() {
		switch ($(this).html()) {
		case ("Easy"):
			$(this).addClass("easy");
			break;
		case ("Moderate"):
			$(this).addClass("moderate");
			break;
		case ("Hard"):
			$(this).addClass("hard");
			break;
		default:
			console.log("oops");
		}
	});
}




*/