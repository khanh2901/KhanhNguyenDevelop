    const url = 'localhost:9000/customer-list'
    fetch(url)
        .then(response => response.json())
        .then(data => {
            /* Draw chart using data */
            const ctx = document.getElementById('myChart').getContext('2d');
            const chart = new Chart(ctx, {
                type: 'horizontalBar', /* Change chart type to horizontal bar chart */
                data: {
                    labels: data.labels,
                    datasets: [{
                        label: 'Sales',
                        data: data.sales,
                        backgroundColor: 'rgba(255, 99, 132, 0.2)',
                        borderColor: 'rgba(255, 99, 132, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        xAxes: [{ /* Change the x-axis to a linear scale */
                            type: 'linear',
                            position: 'bottom',
                            ticks: {
                                beginAtZero: true
                            }
                        }],
                        yAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                    }
                }
            });
        });

