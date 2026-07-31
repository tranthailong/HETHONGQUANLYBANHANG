const API_URL = 'http://localhost:8080/api/products';
const CATEGORY_API_URL = 'http://localhost:8080/api/categories';

document.addEventListener('DOMContentLoaded', () => {
    fetchProducts();
    fetchCategoriesForSelect();

    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.addEventListener('input', function (e) {
            const keyword = e.target.value.toLowerCase();
            const rows = document.querySelectorAll('#productTableBody tr');
            rows.forEach(row => {
                const nameText = row.querySelector('.product-name')?.innerText.toLowerCase() || '';
                if (nameText.includes(keyword)) {
                    row.style.display = '';
                } else {
                    row.style.display = 'none';
                }
            });
        });
    }
});

async function fetchProducts() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error('Không thể tải dữ liệu sản phẩm!');
        const products = await response.json();
        renderTable(products);
    } catch (error) {
        console.error('Lỗi khi tải sản phẩm:', error);
    }
}

function renderTable(products) {
    const tbody = document.getElementById('productTableBody');
    tbody.innerHTML = '';

    if (!products || products.length === 0) {
        tbody.innerHTML = `<tr><td colspan="6" class="p-6 text-center text-slate-400">Chưa có sản phẩm nào trong hệ thống.</td></tr>`;
        return;
    }

    products.forEach(p => {
        const tr = document.createElement('tr');
        tr.className = 'hover:bg-slate-50/80 transition border-b border-slate-100';

        const productPrice = p.big !== undefined ? p.big : (p.price !== undefined ? p.price : 0);

        let categoryDisplay = 'Chưa phân loại';
        if (p.categoryName) {
            categoryDisplay = p.categoryName;
        } else if (p.category && p.category.name) {
            categoryDisplay = p.category.name;
        }

        tr.innerHTML = `
            <td class="p-5 font-semibold text-slate-600">#${p.id}</td>
            <td class="p-5 font-medium text-slate-800 product-name">${p.name}</td>
            <td class="p-5"><span class="bg-blue-50 text-blue-600 px-3 py-1 rounded-full text-xs font-semibold">${categoryDisplay}</span></td>
            <td class="p-5 font-bold text-slate-900">${Number(productPrice).toLocaleString('vi-VN')} đ</td>
            <td class="p-5">
                <span class="${p.quantity > 0 ? 'text-emerald-600 bg-emerald-50' : 'text-rose-600 bg-rose-50'} px-2.5 py-1 rounded-lg text-xs font-bold">
                    ${p.quantity} cái
                </span>
            </td>
            <td class="p-5 text-center space-x-2">
                <button onclick="openEditModal(${p.id})" class="w-9 h-9 rounded-xl bg-amber-50 text-amber-600 hover:bg-amber-100 transition inline-flex items-center justify-center shadow-sm" title="Sửa">
                    <i class="fa-solid fa-pen-to-square"></i>
                </button>
                <button onclick="deleteProduct(${p.id})" class="w-9 h-9 rounded-xl bg-rose-50 text-rose-600 hover:bg-rose-100 transition inline-flex items-center justify-center shadow-sm" title="Xóa">
                    <i class="fa-solid fa-trash"></i>
                </button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

async function fetchCategoriesForSelect() {
    try {
        const response = await fetch(CATEGORY_API_URL);
        if (!response.ok) return;
        const categories = await response.json();
        const select = document.getElementById('categoryId');
        if (!select) return;

        select.innerHTML = '<option value="">-- Chọn danh mục sản phẩm --</option>';
        categories.forEach(c => {
            select.innerHTML += `<option value="${c.id}">${c.name}</option>`;
        });
    } catch (e) {
        console.log('Không thể tải danh mục:', e);
    }
}

function openModal() {
    document.getElementById('productModal').classList.remove('hidden');
    document.getElementById('modalTitle').innerText = 'Thêm mới sản phẩm';
    document.getElementById('productForm').reset();
    document.getElementById('productId').value = '';
}

function closeModal() {
    document.getElementById('productModal').classList.add('hidden');
}

async function handleFormSubmit(event) {
    event.preventDefault();

    const id = document.getElementById('productId').value;
    const catId = parseInt(document.getElementById('categoryId').value);
    const priceVal = parseFloat(document.getElementById('price').value);
    const qtyVal = parseInt(document.getElementById('quantity').value);

    const requestData = {
        name: document.getElementById('name').value,
        big: priceVal,
        quantity: qtyVal,
        categoryId: catId,
        category: { id: catId },
        description: document.getElementById('description').value
    };

    const method = id ? 'PUT' : 'POST';
    const url = id ? `${API_URL}/${id}` : API_URL;

    try {
        const response = await fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(requestData)
        });

        if (!response.ok) {
            let errorMsg = `Lỗi HTTP: ${response.status}`;
            try {
                const errData = await response.json();
                errorMsg = errData.message || JSON.stringify(errData);
            } catch (err) {
                const errText = await response.text();
                if (errText) errorMsg = errText;
            }
            throw new Error(errorMsg);
        }

        alert(id ? 'Cập nhật sản phẩm thành công!' : 'Thêm mới sản phẩm thành công!');
        closeModal();
        fetchProducts();
    } catch (error) {
        alert('Chi tiết lỗi: ' + error.message);
    }
}

async function openEditModal(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        if (!response.ok) throw new Error('Không tìm thấy thông tin sản phẩm!');
        const p = await response.json();

        document.getElementById('productModal').classList.remove('hidden');
        document.getElementById('modalTitle').innerText = 'Cập nhật sản phẩm #' + id;

        document.getElementById('productId').value = p.id;
        document.getElementById('name').value = p.name || '';
        document.getElementById('price').value = p.big !== undefined ? p.big : (p.price !== undefined ? p.price : 0);
        document.getElementById('quantity').value = p.quantity || 0;
        document.getElementById('categoryId').value = p.categoryId || (p.category ? p.category.id : '');
        document.getElementById('description').value = p.description || '';
    } catch (error) {
        alert('Lỗi: ' + error.message);
    }
}

async function deleteProduct(id) {
    if (!confirm(`Bạn có chắc chắn muốn xóa sản phẩm có ID = ${id} không?`)) return;

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });

        if (!response.ok) throw new Error('Xóa sản phẩm thất bại!');

        alert('Xóa sản phẩm thành công!');
        fetchProducts();
    } catch (error) {
        alert('Lỗi: ' + error.message);
    }
}