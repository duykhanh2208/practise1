function showRevenue() {
    let content = ""
    content += `<label>lựa chọn tháng</label>
    <select name="month" id="month">
    <option value=""></option>
    <option value="1">1</option>
    <option value="2">2</option>
    <option value="3">3</option>
    <option value="4">4</option>
    <option value="5">5</option>
    <option value="6">6</option>
    <option value="7">7</option>
    <option value="8">8</option>
    <option value="9">9</option>
    <option value="10">10</option>
    <option value="11">11</option>
    <option value="12">12</option>
</select>
    <label>nhập vào năm</label> <input type="text" id="year">
<button onclick="showChart()">Xem doanh thu</button><br>
<canvas id="myChart" style="width:100%;max-width:600px"></canvas>`
    document.getElementById("display").innerHTML = content;

}

function showChart() {
    let month = document.getElementById("month").value;
    let year = document.getElementById("year").value;

    if (month !== "" && year !== "") {
        let revenue = {
            month: month,
            year: year
        }
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            data: JSON.stringify(revenue),
            url: "http://localhost:8080/api/revenues",
            success: function (data) {

                let xValues = [];
                let yValues=[];
                for (let i = 0; i < data.length; i++) {
                    xValues.push(data[i][0]);
                    yValues.push(data[i][3])
                }

                new Chart("myChart", {
                    type: "line",
                    data: {
                        labels: xValues,
                        datasets: [{
                            fill: false,
                            lineTension: 0,
                            backgroundColor: "rgba(0,0,255,1.0)",
                            borderColor: "rgba(0,0,255,0.1)",
                            data: yValues
                        }]
                    },
                    options: {
                        legend: {display: false},
                        scales: {
                            yAxes: [{ticks: {min: 0, max:50000}}],
                        }
                    }
                });
            }
        })
    } else if (month === "" && year !== "") {
        let revenue = {
            year: year
        }
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            data: JSON.stringify(revenue),
            url: "http://localhost:8080/api/revenues/showListByYear",
            success: function (data) {

                let xValues = [];
                let yValues=[];
                for (let i = 0; i < data.length; i++) {
                    console.log(data[i]);
                    console.log(data[i][0]);
                    console.log(data[i][1]);
                    console.log(data[i][2]);

                    xValues.push(data[i][1]);
                    yValues.push(data[i][3])
                }

                new Chart("myChart", {
                    type: "line",
                    data: {
                        labels: xValues,
                        datasets: [{
                            fill: false,
                            lineTension: 0,
                            backgroundColor: "rgba(0,0,255,1.0)",
                            borderColor: "rgba(0,0,255,0.1)",
                            data: yValues
                        }]
                    },
                    options: {
                        legend: {display: false},
                        scales: {
                            yAxes: [{ticks: {min: 0, max:50000}}],
                        }
                    }
                });
            }
        })
    } else {
        alert("Bạn cần chọn năm cần xem")
    }
}
