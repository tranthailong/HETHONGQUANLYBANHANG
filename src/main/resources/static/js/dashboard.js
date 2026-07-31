document.addEventListener("DOMContentLoaded", function () {
    loadDashboardData();
});

function loadDashboardData() {
    fetch('/api/dashboard')
        .then(res => {
            if (!res.ok) throw new Error("HTTP error! Status: " + res.status);
            return res.json();
        })
        .then(data => {
            console.log(">>> DATA MYSQL TRẢ VỀ THÀNH CÔNG:", data);

            // 1. Đưa số liệu vào HTML
            document.getElementById('totalRevenue').innerText = Number(data.totalRevenue || 0).toLocaleString('vi-VN') + ' đ';
            document.getElementById('totalOrders').innerText = Number(data.totalOrders || 0).toLocaleString('vi-VN');
            document.getElementById('totalProducts').innerText = Number(data.totalProducts || 0).toLocaleString('vi-VN');
            document.getElementById('activeProducts').innerText = Number(data.activeProducts || 0).toLocaleString('vi-VN');
            document.getElementById('totalCustomers').innerText = Number(data.totalCustomers || 0).toLocaleString('vi-VN');
            document.getElementById('totalCategories').innerText = Number(data.totalCategories || 0).toLocaleString('vi-VN');
            document.getElementById('lowStockProducts').innerText = Number(data.lowStockProducts || 0).toLocaleString('vi-VN');
            document.getElementById('outOfStockProducts').innerText = Number(data.outOfStockProducts || 0).toLocaleString('vi-VN');

            // 2. Vẽ biểu đồ tích hợp đa trục
            renderChart(data);
        })
        .catch(err => {
            console.error("Lỗi khi tải dữ liệu API /api/dashboard:", err);
        });
}

function renderChart(data) {
    const chartElement = document.getElementById('revenueChart');
    if (!chartElement || typeof Chart === 'undefined') return;

    const ctx = chartElement.getContext('2d');

    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Tổng Đơn Hàng', 'Sản Phẩm Active', 'Tổng Khách Hàng', 'Tổng Danh Mục', 'Doanh Thu (VNĐ)'],
            datasets: [{
                label: 'Giá trị thống kê',
                data: [
                    data.totalOrders || 0,
                    data.activeProducts || 0,
                    data.totalCustomers || 0,
                    data.totalCategories || 0,
                    data.totalRevenue || 0 // Đưa trực tiếp doanh thu vào đây
                ],
                backgroundColor: ['#22c55e', '#0284c7', '#9333ea', '#f59e0b', '#2563eb'],
                borderRadius: 6,
                // Gán cột Doanh Thu sang trục Y phụ bên phải, các cột còn lại ở trục trái
                yAxisID: ['y', 'y', 'y', 'y', 'y1']
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: { display: false }
            },
            scales: {
                y: {
                    type: 'linear',
                    position: 'left',
                    beginAtZero: true,
                    title: { display: true, text: 'Số lượng' },
                    ticks: { precision: 0 }
                },
                y1: {
                    type: 'linear',
                    position: 'right', // Trục riêng biệt bên phải cho tiền triệu/tỷ
                    beginAtZero: true,
                    grid: { drawOnChartArea: false }, // Không làm rối lưới biểu đồ
                    title: { display: true, text: 'Doanh Thu (VNĐ)' },
                    ticks: {
                        callback: function(value) {
                            return value.toLocaleString('vi-VN') + ' đ';
                        }
                    }
                }
            }
        }
    });
}

document.addEventListener("DOMContentLoaded", function () {
    loadDashboardData();
});

function loadDashboardData() {
    fetch('/api/dashboard')
        .then(res => {
            if (!res.ok) throw new Error("HTTP error! Status: " + res.status);
            return res.json();
        })
        .then(data => {
            console.log(">>> DATA MYSQL TRẢ VỀ THÀNH CÔNG:", data);

            // 1. Đưa số liệu vào HTML
            document.getElementById('totalRevenue').innerText = Number(data.totalRevenue || 0).toLocaleString('vi-VN') + ' đ';
            document.getElementById('totalOrders').innerText = Number(data.totalOrders || 0).toLocaleString('vi-VN');
            document.getElementById('totalProducts').innerText = Number(data.totalProducts || 0).toLocaleString('vi-VN');
            document.getElementById('activeProducts').innerText = Number(data.activeProducts || 0).toLocaleString('vi-VN');
            document.getElementById('totalCustomers').innerText = Number(data.totalCustomers || 0).toLocaleString('vi-VN');
            document.getElementById('totalCategories').innerText = Number(data.totalCategories || 0).toLocaleString('vi-VN');
            document.getElementById('lowStockProducts').innerText = Number(data.lowStockProducts || 0).toLocaleString('vi-VN');
            document.getElementById('outOfStockProducts').innerText = Number(data.outOfStockProducts || 0).toLocaleString('vi-VN');

            // 2. Vẽ biểu đồ chuẩn đa trục
            renderChart(data);
        })
        .catch(err => {
            console.error("Lỗi khi tải dữ liệu API /api/dashboard:", err);
        });
}

function renderChart(data) {
    const chartElement = document.getElementById('revenueChart');
    if (!chartElement || typeof Chart === 'undefined') return;

    const ctx = chartElement.getContext('2d');

    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Tổng Đơn Hàng', 'Sản Phẩm Active', 'Tổng Khách Hàng', 'Tổng Danh Mục', 'Doanh Thu (VNĐ)'],
            datasets: [
                {
                    label: 'Số lượng',
                    data: [
                        data.totalOrders || 0,
                        data.activeProducts || 0,
                        data.totalCustomers || 0,
                        data.totalCategories || 0,
                        null // Bỏ trống ở cột doanh thu để dataset này không bị lỗi
                    ],
                    backgroundColor: ['#22c55e', '#0284c7', '#9333ea', '#f59e0b', 'transparent'],
                    borderRadius: 6,
                    yAxisID: 'y' // Gắn vào trục Y bên trái (số lượng nhỏ)
                },
                {
                    label: 'Doanh Thu (VNĐ)',
                    data: [
                        null, null, null, null,
                        data.totalRevenue || 0 // Chỉ đặt giá trị doanh thu ở đúng vị trí cột cuối
                    ],
                    backgroundColor: '#2563eb',
                    borderRadius: 6,
                    yAxisID: 'y1' // Gắn vào trục Y phụ bên phải (tiền triệu)
                }
            ]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: { display: true } // Hiển thị chú thích để phân biệt rõ cột số lượng và cột doanh thu
            },
            scales: {
                y: {
                    type: 'linear',
                    position: 'left',
                    beginAtZero: true,
                    title: { display: true, text: 'Quy mô số lượng' },
                    ticks: { precision: 0 }
                },
                y1: {
                    type: 'linear',
                    position: 'right',
                    beginAtZero: true,
                    grid: { drawOnChartArea: false }, // Tránh làm rối lưới biểu đồ
                    title: { display: true, text: 'Quy mô doanh thu (VNĐ)' },
                    ticks: {
                        callback: function(value) {
                            return value.toLocaleString('vi-VN') + ' đ';
                        }
                    }
                }
            }
        }
    });
}