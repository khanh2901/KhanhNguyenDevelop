// Age Discrete Bar Chart
nv.addGraph(function() {
  var chart = nv.models.discreteBarChart()
      .x(function(d) { return d.label })    //Specify the data accessors.
      .y(function(d) { return d.value })
      .staggerLabels(false)    // This automatically
      .tooltips(true)        //Don't show tooltips
      .showValues(false)       //...instead, show the bar value right on top of each bar.
      .color(['#193c67', '#1ba1dc', '#b74424', '#fc6a22', '#81ae7e', '#ec413e', '#f39388', '#736964', '#951c58', '#0e6bb3', '#fc6a22', '#221f57', '#a49d9b', '#f8dc46', '#337355', '#d01925', '#fecb88'])
      ;

  d3.select('#ageChart svg')
      .datum(ageData())
      .call(chart);

  nv.utils.windowResize(chart.update);

  return chart;
});

// Income Discrete Bar Chart
nv.addGraph(function() {
  var chart = nv.models.discreteBarChart()
      .x(function(d) { return d.label })    //Specify the data accessors.
      .y(function(d) { return d.value })
      .staggerLabels(false)    // This automatically
      .tooltips(true)        //Don't show tooltips
      .showValues(false)       //...instead, show the bar value right on top of each bar.
      .color(['#193c67', '#1ba1dc', '#b74424', '#fc6a22', '#81ae7e', '#ec413e', '#f39388', '#736964', '#951c58', '#0e6bb3', '#fc6a22', '#221f57', '#a49d9b', '#f8dc46', '#337355', '#d01925', '#fecb88'])
      ;

  d3.select('#incomeChart svg')
      .datum(incomeData())
      .call(chart);

  nv.utils.windowResize(chart.update);

  return chart;
});

// Education Discrete Bar Chart
nv.addGraph(function() {
  var chart = nv.models.discreteBarChart()
      .x(function(d) { return d.label })    //Specify the data accessors.
      .y(function(d) { return d.value })
      .staggerLabels(false)    // This automatically
      .tooltips(true)        //Don't show tooltips
      .showValues(false)       //...instead, show the bar value right on top of each bar.
      .color(['#193c67', '#1ba1dc', '#b74424', '#fc6a22', '#81ae7e', '#ec413e', '#f39388', '#736964', '#951c58', '#0e6bb3', '#fc6a22', '#221f57', '#a49d9b', '#f8dc46', '#337355', '#d01925', '#fecb88'])
      ;

  d3.select('#educationChart svg')
      .datum(educationData())
      .call(chart);

  nv.utils.windowResize(chart.update);

  return chart;
});


//Each bar represents a single discrete quantity.
function ageData() {
 return  [
    {
      key: "Age",
      values: [
        {
          "label" : "0-5",
          "value" : 10
        },
        {
          "label" : "5-10",
          "value" : 5
        },
        {
          "label" : "10-15",
          "value" : 3
        },
        {
          "label" : "15-20",
          "value" : 4
        },
        {
          "label" : "20-25",
          "value" : 6
        },
        {
          "label" : "25-30",
          "value" : 8
        } ,
        {
          "label" : "30-35",
          "value" : 4
        } ,
        {
          "label" : "35-40",
          "value" : 9
        },
        {
          "label" : "40-45",
          "value" : 5
        },
        {
          "label" : "45-50",
          "value" : 3
        },
        {
          "label" : "50-55",
          "value" : 4
        },
        {
          "label" : "55-60",
          "value" : 7
        },
        {
          "label" : "60-65",
          "value" : 9
        },
        {
          "label" : "65-70",
          "value" : 5
        },
        {
          "label" : "70-75",
          "value" : 7
        },
        {
          "label" : "75-80",
          "value" : 9
        },
        {
          "label" : "80+",
          "value" : 5
        }
      ]
    }
  ]
}

function incomeData() {
 return  [
    {
      key: "Income",
      values: [
        {
          "label" : "0-10",
          "value" : 10
        },
        {
          "label" : "10-15",
          "value" : 5
        },
        {
          "label" : "15-20",
          "value" : 3
        },
        {
          "label" : "20-25",
          "value" : 4
        },
        {
          "label" : "25-30",
          "value" : 6
        },
        {
          "label" : "30-35",
          "value" : 8
        } ,
        {
          "label" : "35-40",
          "value" : 4
        } ,
        {
          "label" : "40-45",
          "value" : 9
        },
        {
          "label" : "45-50",
          "value" : 5
        },
        {
          "label" : "50-60",
          "value" : 3
        },
        {
          "label" : "60-75",
          "value" : 4
        },
        {
          "label" : "75-100",
          "value" : 7
        },
        {
          "label" : "100-125",
          "value" : 9
        },
        {
          "label" : "125-150",
          "value" : 5
        },
        {
          "label" : "150-200",
          "value" : 7
        },
        {
          "label" : "200+",
          "value" : 9
        }
      ]
    }
  ]
}

function educationData() {
 return  [
    {
      key: "Education",
      values: [
        {
          "label" : "None",
          "value" : 10
        },
        {
          "label" : "No High School",
          "value" : 5
        },
        {
          "label" : "Some High School",
          "value" : 3
        },
        {
          "label" : "High School",
          "value" : 4
        },
        {
          "label" : "Some College",
          "value" : 6
        },
        {
          "label" : "Associate",
          "value" : 8
        } ,
        {
          "label" : "Bachelor",
          "value" : 4
        } ,
        {
          "label" : "Master",
          "value" : 9
        },
        {
          "label" : "PHD",
          "value" : 5
        }
      ]
    }
  ]
}
