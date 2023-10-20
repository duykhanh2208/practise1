function goBackTohome() {
   window.location.href="/FE_Practise1/Home/Home.html";
}

function register() {

    let account = document.getElementById("account").value;
    let password = document.getElementById("password").value;
    let role_id = document.getElementById("role").value;
    if(account!==""||password!==""||role_id!=="") {
        let user1 = {
            account: account,
            password: password,
            role: {
                id: role_id
            }
        }
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            data: JSON.stringify(user1),
            url: "http://localhost:8080/api/users/create",
            success: function (data) {
                alert("Chào mừng " + account + " đã đăng ký thành công, chúc bạn mua sắm vui vẻ!!!")
                let cart = {
                    user: data
                }
                $.ajax({
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    type: "POST",
                    data: JSON.stringify(cart),
                    url: "http://localhost:8080/api/carts/createCart",
                    success: function (data) {
                        console.log(data);
                        alert("Cart tạo thành công!!!")

                    }
                })
            }
        })
    }else {
        alert("Nhập đủ thông tin đi mày!!!")
    }
}
function createUser(){
    let account = document.getElementById("account").value;
    let password = document.getElementById("password").value;
    let role = document.getElementById("role").value;
    let user1 = {
        account: account,
        password: password,
        role: role
    }
    let cart1 = {
        user: user
    }

    $.ajax({
        type: "POST",
        data: JSON.stringify(user1),
        url: "http://localhost:8080/api/users/create",
        success: function (data) {
            console.log(data)
            let account = data.account;
            alert("Chào mừng " + account + " đã đăng ký thành công, chúc bạn mua sắm vui vẻ!!!")
        }
    })

}