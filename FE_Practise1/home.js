function loadDisplay() {

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/products",
        success: function (data) {
            let content = ""
            content += `<table style="width: 100%">
        <tr>
        <th>STT</th>
        <th>Tên sản phẩm</th>
        <th>Đơn giá</th>
        <th>Số lượng</th>
        <th>Ngày sản xuất</th>
        <th>Nhà cung cấp</th>
        <th>Kho</th>
        <th>Nhập số lượng thêm</th>
        <th colspan="2"></th>
        </tr>`
            for (let i = 0; i < data.length; i++) {
                content += `<tr>
            <td>${i + 1}</td>
            <td>${data[i].name}</td>
            <td>${data[i].price}</td>
            <td>${data[i].quantity}</td>
            <td>${data[i].date}</td>
            <td>${data[i].provider}</td>
            <td>${data[i].wareHouse.name}</td>
            <td><input type="text" id="quantity"></td>
            <td><button onclick="addToCart(${data[i].id})">Mua hàng</button></td>
            <td><button onclick="viewDetail(${data[i].id})">Xem chi tiết</button></td>
            </tr>`
            }
            content += `</table>`
            document.getElementById("display").innerHTML = content;
        }
    })
    let content1 = ""
    content1 += `<table  style="width: 100%">
                <tr><td><button onclick="showAllCartDetail()"><a>Xem giỏ hàng</a></button></td></tr>
                <tr><td><button onclick="showBill()"><a>Xem hóa đơn</a></button></td></tr>
                <tr><td><button onclick="showProduct()"><a>Sản phẩm</a></button></td></tr>
                <tr><td><button onclick="showRepository()"><a>Kho bãi</a></button></td></tr>
                <tr><td><button onclick="showRevenue()"><a>Xem báo cáo doanh thu</a></button></td></tr>
                <tr><td><a href="">Mua hàng 3D</a></td></tr>
               

                 </table>`
    document.getElementById("div1").innerHTML=content1;

    let userLocalStorage = localStorage.getItem("user_practise1");
    let obj = JSON.parse(userLocalStorage);
    let contentAccount = ""
    contentAccount+=`<table style=" float: right; text-align: right" >
        <tr>
            <td><lable>${obj.account}</lable></td>
            <td><button onclick="LogIn()" style=" float: right; text-align: right">Đăng nhập</button></td>
            <td><button onclick="register()" style=" float: right; text-align: right" >đăng ký</button></td>
        </tr>
    </table>`
    document.getElementById("showAccount").innerHTML=contentAccount;
}

function LogIn() {
    window.location.href = "/FE_Practise1/Home/Log.html"
}

function addToCart(){
      if(confirm("Bạn có muốn đăng nhập không?")){
        window.location.href="/FE_Practise1/Home/Log.html";
    }
}
function register(){
    window.location.href="/FE_Practise1/Home/register.html";
}


