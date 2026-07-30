async function loadDashboard() {

    try {

        const response = await fetch("/api/dashboard");

        const data = await response.json();

        document.getElementById("totalProducts").innerText = data.totalProducts;
        document.getElementById("activeProducts").innerText = data.activeProducts;
        document.getElementById("lowStockProducts").innerText = data.lowStockProducts;
        document.getElementById("outOfStockProducts").innerText = data.outOfStockProducts;

        document.getElementById("totalCategories").innerText = data.totalCategories;
        document.getElementById("totalCustomers").innerText = data.totalCustomers;
        document.getElementById("totalOrders").innerText = data.totalOrders;

        document.getElementById("totalRevenue").innerText =
            Number(data.totalRevenue).toLocaleString("vi-VN") + " ₫";

        document.getElementById("tbProducts").innerText = data.totalProducts;
        document.getElementById("tbCategories").innerText = data.totalCategories;
        document.getElementById("tbCustomers").innerText = data.totalCustomers;
        document.getElementById("tbOrders").innerText = data.totalOrders;

        document.getElementById("tbRevenue").innerText =
            Number(data.totalRevenue).toLocaleString("vi-VN") + " ₫";

    } catch (e) {

        console.error(e);

        alert("Không thể tải dữ liệu Dashboard");

    }

}

loadDashboard();