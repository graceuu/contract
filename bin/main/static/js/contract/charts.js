function drawLineChart(chartObjId, chartData) {
	const chartObj = document.getElementById(chartObjId);
	
	if (!chartObj)
		return;
	
	const orderedData = sortData(chartData);
	
	new Chart(chartObj, {
		type: 'line',
		data: {
			labels: Object.keys(orderedData),
			datasets: [{
				label: '건',
				lineTension: 0.3,
				backgroundColor: 'rgba(2,117,216,0.2)',
				borderColor: 'rgba(2,117,216,1)',
				pointRadius: 5,
				pointBackgroundColor: 'rgba(2,117,216,1)',
				pointBorderColor: 'rgba(255,255,255,0.8)',
				pointHoverRadius: 5,
				pointHoverBackgroundColor: 'rgba(2,117,216,1)',
				pointHitRadius: 50,
				pointBorderWidth: 2,
				data: Object.values(orderedData)
			}],
		},
		options: {
			scales: {
				xAxes: [{
					time: {
						unit: 'date'
					},
					gridLines: {
						display: false
					},
					ticks: {
						maxTicksLimit: 7
					}
				}],
				yAxes: [{
					ticks: {
						min: 0,
						max: 10,
						maxTicksLimit: 5
					},
					gridLines: {
						color: 'rgba(0, 0, 0, .125)'
					}
				}]
			},
			legend: {
				display: false
			}
		}
	});
}

function drawBarChart(chartObjId, chartData) {
	const chartObj = document.getElementById(chartObjId);
	
	if (!chartObj)
		return;
	
	const orderedData = sortData(chartData);
	
	new Chart(chartObj, {
		type: 'bar',
		data: {
			labels: Object.keys(orderedData),
			datasets: [{
				label: '원',
				backgroundColor: 'rgba(2,117,216,1)',
				borderColor: 'rgba(2,117,216,1)',
				data: Object.values(orderedData)
			}],
		},
		options: {
			scales: {
				xAxes: [{
					time: {
						unit: 'date'
					},
					gridLines: {
						display: false
					},
					ticks: {
						maxTicksLimit: 6
					}
				}],
				yAxes: [{
					ticks: {
						min: 0,
						max: 5000000,
						maxTicksLimit: 5
					},
					gridLines: {
						display: true
					}
				}]
			},
			legend: {
				display: false
			}
		}
	});
}

function sortData(unorderedData) {
	const orderedData = {};
	Object.keys(unorderedData).sort().forEach(function(key) {
		orderedData[key] = unorderedData[key];
	});
	return orderedData;
}

export default {
	drawLineChart,
	drawBarChart
}