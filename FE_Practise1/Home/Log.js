function login() {

    let account = document.getElementById("account").value;
    let password = document.getElementById("password").value;
    let user = {
        account: account,
        password: password
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(user),
        url: "http://localhost:8080/api/users/findByAccount",
        success: function (data) {
            console.log(data);
            let user_practise1 = JSON.stringify(data)
            localStorage.setItem("user_practise1", user_practise1);
            if(data.account==="Not Found"){
                window.location.href="/FE_Practise1/Home/Home.html";
                alert("Không tìm thấy account")
            }else if(data.role.name==="user"){
                window.location.href="/FE_Practise1/User/HomeUser.html";
            }else if(data.role.name==="shop")
            {
                window.location.href="/FE_Practise1/Shop/HomeShop.html";
            } else if(data.role.name==="admin"){
                window.location.href="/FE_Practise1/Admin/HomeAdmin.html";
            }
        }
    })

}