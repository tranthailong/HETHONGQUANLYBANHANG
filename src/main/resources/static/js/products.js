document.addEventListener("DOMContentLoaded", () => {

    const API_URL = "/api/products";

    let products = [];

    let categories = [];

    let currentPage = 1;

    const itemsPerPage = 5;

    let deleteProductId = null;


    const tableBody =
        document.getElementById("productTableBody");

    const tableWrapper =
        document.getElementById("tableWrapper");

    const loadingState =
        document.getElementById("loadingState");

    const emptyState =
        document.getElementById("emptyState");

    const errorState =
        document.getElementById("errorState");

    const errorMessage =
        document.getElementById("errorMessage");

    const searchInput =
        document.getElementById("searchInput");

    const categoryFilter =
        document.getElementById("categoryFilter");

    const stockFilter =
        document.getElementById("stockFilter");

    const sortFilter =
        document.getElementById("sortFilter");

    const productModal =
        document.getElementById("productModal");

    const detailModal =
        document.getElementById("detailModal");

    const deleteModal =
        document.getElementById("deleteModal");

    const productForm =
        document.getElementById("productForm");


    function formatCurrency(value) {

        return new Intl.NumberFormat(
            "vi-VN",
            {
                style: "currency",
                currency: "VND"
            }
        ).format(value || 0);

    }


    function showLoading() {

        loadingState.style.display = "flex";

        tableWrapper.style.display = "none";

        emptyState.style.display = "none";

        errorState.style.display = "none";

    }


    function hideLoading() {

        loadingState.style.display = "none";

    }


    function showError(message) {

        hideLoading();

        tableWrapper.style.display = "none";

        emptyState.style.display = "none";

        errorState.style.display = "flex";

        errorMessage.textContent = message;

    }


    async function loadProducts() {

        showLoading();

        try {

            const response =
                await fetch(API_URL);

            if (!response.ok) {

                throw new Error(
                    `HTTP ${response.status}`
                );

            }

            products =
                await response.json();

            hideLoading();

            tableWrapper.style.display =
                "block";

            loadCategories();

            renderProducts();

        } catch (error) {

            console.error(
                "Lỗi tải sản phẩm:",
                error
            );

            showError(
                "Không thể kết nối đến API sản phẩm. Hãy kiểm tra Spring Boot và MySQL."
            );

        }

    }


    async function loadCategories() {

        try {

            const response =
                await fetch(
                    "/api/categories"
                );

            if (!response.ok) {

                throw new Error(
                    "Không thể tải danh mục"
                );

            }

            categories =
                await response.json();

            renderCategoryFilters();

        } catch (error) {

            console.error(
                "Lỗi danh mục:",
                error
            );

        }

    }


    function renderCategoryFilters() {

        const currentFilter =
            categoryFilter.value;

        categoryFilter.innerHTML = `
            <option value="all">
                Tất cả danh mục
            </option>
        `;

        const productCategorySelect =
            document.getElementById(
                "productCategory"
            );

        productCategorySelect.innerHTML = `
            <option value="">
                -- Chọn danh mục --
            </option>
        `;


        categories.forEach(category => {

            const filterOption =
                document.createElement(
                    "option"
                );

            filterOption.value =
                category.id;

            filterOption.textContent =
                category.name;

            categoryFilter.appendChild(
                filterOption
            );


            const formOption =
                document.createElement(
                    "option"
                );

            formOption.value =
                category.id;

            formOption.textContent =
                category.name;

            productCategorySelect.appendChild(
                formOption
            );

        });


        categoryFilter.value =
            currentFilter || "all";

    }


    function getCategoryName(categoryId) {

        const category =
            categories.find(
                item =>
                    Number(item.id) ===
                    Number(categoryId)
            );

        return category
            ? category.name
            : "Chưa phân loại";

    }


    function getFilteredProducts() {

        const keyword =
            searchInput.value
                .toLowerCase()
                .trim();


        let result =
            products.filter(product => {

                const name =
                    (product.name || "")
                        .toLowerCase();

                const description =
                    (product.description || "")
                        .toLowerCase();

                const matchSearch =
                    name.includes(keyword) ||
                    description.includes(keyword);


                const matchCategory =
                    categoryFilter.value === "all" ||
                    Number(product.categoryId) ===
                    Number(categoryFilter.value);


                let matchStock = true;


                if (
                    stockFilter.value ===
                    "available"
                ) {

                    matchStock =
                        product.quantity > 10;

                }


                if (
                    stockFilter.value ===
                    "low"
                ) {

                    matchStock =
                        product.quantity > 0 &&
                        product.quantity <= 10;

                }


                if (
                    stockFilter.value ===
                    "out"
                ) {

                    matchStock =
                        product.quantity <= 0;

                }


                return (
                    matchSearch &&
                    matchCategory &&
                    matchStock
                );

            });


        switch (
            sortFilter.value
            ) {

            case "nameAsc":

                result.sort(
                    (a, b) =>
                        a.name.localeCompare(
                            b.name,
                            "vi"
                        )
                );

                break;


            case "nameDesc":

                result.sort(
                    (a, b) =>
                        b.name.localeCompare(
                            a.name,
                            "vi"
                        )
                );

                break;


            case "priceAsc":

                result.sort(
                    (a, b) =>
                        a.price - b.price
                );

                break;


            case "priceDesc":

                result.sort(
                    (a, b) =>
                        b.price - a.price
                );

                break;


            case "quantityAsc":

                result.sort(
                    (a, b) =>
                        a.quantity -
                        b.quantity
                );

                break;


            case "quantityDesc":

                result.sort(
                    (a, b) =>
                        b.quantity -
                        a.quantity
                );

                break;

        }


        return result;

    }


    function getProductStatus(quantity) {

        if (quantity <= 0) {

            return {
                text: "Hết hàng",
                className: "out"
            };

        }


        if (quantity <= 10) {

            return {
                text: "Sắp hết hàng",
                className: "low"
            };

        }


        return {
            text: "Còn hàng",
            className: "success"
        };

    }


    function renderProducts() {

        const filtered =
            getFilteredProducts();


        const total =
            filtered.length;


        const totalPages =
            Math.ceil(
                total / itemsPerPage
            );


        if (
            currentPage > totalPages &&
            totalPages > 0
        ) {

            currentPage =
                totalPages;

        }


        const start =
            (currentPage - 1) *
            itemsPerPage;


        const currentProducts =
            filtered.slice(
                start,
                start + itemsPerPage
            );


        tableBody.innerHTML = "";


        if (
            currentProducts.length === 0
        ) {

            tableWrapper.style.display =
                "none";

            emptyState.style.display =
                "flex";

        } else {

            tableWrapper.style.display =
                "block";

            emptyState.style.display =
                "none";


            currentProducts.forEach(
                product => {

                    const status =
                        getProductStatus(
                            product.quantity
                        );


                    const row =
                        document.createElement(
                            "tr"
                        );


                    row.innerHTML = `

                        <td>
                            #${product.id}
                        </td>

                        <td>

                            <div class="product-name">

                                <div class="product-image">

                                    <i class="bi bi-box-seam"></i>

                                </div>

                                <div>

                                    <strong>
                                        ${escapeHtml(product.name)}
                                    </strong>

                                    <small>
                                        ${escapeHtml(
                        product.image ||
                        "Chưa có hình ảnh"
                    )}
                                    </small>

                                </div>

                            </div>

                        </td>

                        <td>
                            ${escapeHtml(
                        product.description ||
                        "Chưa có mô tả"
                    )}
                        </td>

                        <td>

                            <span class="category-badge">

                                ${escapeHtml(
                        getCategoryName(
                            product.categoryId
                        )
                    )}

                            </span>

                        </td>

                        <td>

                            <strong>

                                ${formatCurrency(
                        product.price
                    )}

                            </strong>

                        </td>

                        <td>

                            <span class="
                                stock-number
                                ${
                        product.quantity <= 10
                            ? "stock-low"
                            : ""
                    }
                                ${
                        product.quantity <= 0
                            ? "stock-out"
                            : ""
                    }
                            ">

                                ${product.quantity}

                            </span>

                        </td>

                        <td>

                            <span class="
                                status
                                ${status.className}
                            ">

                                <i class="bi bi-circle-fill"></i>

                                ${status.text}

                            </span>

                        </td>

                        <td>

                            <div class="action-buttons">

                                <button
                                    class="btn-action view"
                                    data-id="${product.id}">

                                    <i class="bi bi-eye"></i>

                                </button>

                                <button
                                    class="btn-action edit"
                                    data-id="${product.id}">

                                    <i class="bi bi-pencil"></i>

                                </button>

                                <button
                                    class="btn-action delete"
                                    data-id="${product.id}">

                                    <i class="bi bi-trash"></i>

                                </button>

                            </div>

                        </td>

                    `;


                    tableBody.appendChild(
                        row
                    );

                }
            );

        }


        updatePagination(
            total,
            start,
            currentProducts.length
        );


        updateStatistics();

    }


    function updateStatistics() {

        const total =
            products.length;


        const active =
            products.filter(
                product =>
                    product.quantity > 10
            ).length;


        const low =
            products.filter(
                product =>
                    product.quantity > 0 &&
                    product.quantity <= 10
            ).length;


        const out =
            products.filter(
                product =>
                    product.quantity <= 0
            ).length;


        document.getElementById(
            "totalProducts"
        ).textContent = total;


        document.getElementById(
            "activeProducts"
        ).textContent = active;


        document.getElementById(
            "lowStockProducts"
        ).textContent = low;


        document.getElementById(
            "outOfStockProducts"
        ).textContent = out;

    }


    function updatePagination(
        total,
        start,
        count
    ) {

        const pagination =
            document.getElementById(
                "pagination"
            );


        pagination.innerHTML = "";


        const totalPages =
            Math.ceil(
                total / itemsPerPage
            );


        document.getElementById(
            "showingFrom"
        ).textContent =
            total === 0
                ? 0
                : start + 1;


        document.getElementById(
            "showingTo"
        ).textContent =
            start + count;


        document.getElementById(
            "showingTotal"
        ).textContent =
            total;


        if (
            totalPages <= 1
        ) {

            return;

        }


        const previous =
            document.createElement(
                "button"
            );


        previous.innerHTML =
            '<i class="bi bi-chevron-left"></i>';


        previous.disabled =
            currentPage === 1;


        previous.onclick =
            () => {

                if (
                    currentPage > 1
                ) {

                    currentPage--;

                    renderProducts();

                }

            };


        pagination.appendChild(
            previous
        );


        for (
            let i = 1;
            i <= totalPages;
            i++
        ) {

            const button =
                document.createElement(
                    "button"
                );


            button.textContent = i;


            if (
                i === currentPage
            ) {

                button.classList.add(
                    "active"
                );

            }


            button.onclick =
                () => {

                    currentPage = i;

                    renderProducts();

                };


            pagination.appendChild(
                button
            );

        }


        const next =
            document.createElement(
                "button"
            );


        next.innerHTML =
            '<i class="bi bi-chevron-right"></i>';


        next.disabled =
            currentPage === totalPages;


        next.onclick =
            () => {

                if (
                    currentPage <
                    totalPages
                ) {

                    currentPage++;

                    renderProducts();

                }

            };


        pagination.appendChild(
            next
        );

    }


    function openAddModal() {

        productForm.reset();

        document.getElementById(
            "productId"
        ).value = "";

        document.getElementById(
            "modalTitle"
        ).textContent =
            "Thêm sản phẩm";

        productModal.classList.add(
            "show"
        );

    }


    function openEditModal(id) {

        const product =
            products.find(
                item =>
                    Number(item.id) ===
                    Number(id)
            );


        if (!product) {

            return;

        }


        document.getElementById(
            "productId"
        ).value =
            product.id;


        document.getElementById(
            "productName"
        ).value =
            product.name || "";


        document.getElementById(
            "productCategory"
        ).value =
            product.categoryId || "";


        document.getElementById(
            "productPrice"
        ).value =
            product.price || 0;


        document.getElementById(
            "productQuantity"
        ).value =
            product.quantity || 0;


        document.getElementById(
            "productImage"
        ).value =
            product.image || "";


        document.getElementById(
            "productDescription"
        ).value =
            product.description || "";


        document.getElementById(
            "modalTitle"
        ).textContent =
            "Chỉnh sửa sản phẩm";


        productModal.classList.add(
            "show"
        );

    }


    function openDetailModal(id) {

        const product =
            products.find(
                item =>
                    Number(item.id) ===
                    Number(id)
            );


        if (!product) {

            return;

        }


        document.getElementById(
            "detailId"
        ).textContent =
            "#" + product.id;


        document.getElementById(
            "detailName"
        ).textContent =
            product.name;


        document.getElementById(
            "detailCategory"
        ).textContent =
            getCategoryName(
                product.categoryId
            );


        document.getElementById(
            "detailPrice"
        ).textContent =
            formatCurrency(
                product.price
            );


        document.getElementById(
            "detailQuantity"
        ).textContent =
            product.quantity;


        document.getElementById(
            "detailImage"
        ).textContent =
            product.image ||
            "Chưa có";


        document.getElementById(
            "detailDescription"
        ).textContent =
            product.description ||
            "Chưa có mô tả.";


        detailModal.classList.add(
            "show"
        );

    }


    async function saveProduct(event) {

        event.preventDefault();


        const id =
            document.getElementById(
                "productId"
            ).value;


        const productData = {

            name:
                document.getElementById(
                    "productName"
                ).value.trim(),

            description:
                document.getElementById(
                    "productDescription"
                ).value.trim(),

            price:
                Number(
                    document.getElementById(
                        "productPrice"
                    ).value
                ),

            quantity:
                Number(
                    document.getElementById(
                        "productQuantity"
                    ).value
                ),

            image:
                document.getElementById(
                    "productImage"
                ).value.trim(),

            categoryId:
                Number(
                    document.getElementById(
                        "productCategory"
                    ).value
                )

        };


        try {

            const url =
                id
                    ? `${API_URL}/${id}`
                    : API_URL;


            const method =
                id
                    ? "PUT"
                    : "POST";


            const response =
                await fetch(
                    url,
                    {
                        method: method,

                        headers: {
                            "Content-Type":
                                "application/json"
                        },

                        body:
                            JSON.stringify(
                                productData
                            )
                    }
                );


            if (!response.ok) {

                const message =
                    await response.text();

                throw new Error(
                    message ||
                    "Không thể lưu sản phẩm"
                );

            }


            closeAllModals();

            await loadProducts();


            alert(
                id
                    ? "Cập nhật sản phẩm thành công!"
                    : "Thêm sản phẩm thành công!"
            );

        } catch (error) {

            console.error(error);

            alert(
                "Lỗi: " +
                error.message
            );

        }

    }


    async function deleteProduct() {

        if (!deleteProductId) {

            return;

        }


        try {

            const response =
                await fetch(
                    `${API_URL}/${deleteProductId}`,
                    {
                        method: "DELETE"
                    }
                );


            if (!response.ok) {

                throw new Error(
                    "Không thể xóa sản phẩm"
                );

            }


            deleteProductId =
                null;


            closeAllModals();

            await loadProducts();


            alert(
                "Xóa sản phẩm thành công!"
            );

        } catch (error) {

            console.error(error);

            alert(
                "Không thể xóa sản phẩm: " +
                error.message
            );

        }

    }


    function closeAllModals() {

        productModal.classList.remove(
            "show"
        );

        detailModal.classList.remove(
            "show"
        );

        deleteModal.classList.remove(
            "show"
        );

    }


    function escapeHtml(value) {

        const div =
            document.createElement(
                "div"
            );

        div.textContent =
            value || "";

        return div.innerHTML;

    }


    productForm.addEventListener(
        "submit",
        saveProduct
    );


    tableBody.addEventListener(
        "click",
        event => {

            const button =
                event.target.closest(
                    "button"
                );


            if (!button) {

                return;

            }


            const id =
                Number(
                    button.dataset.id
                );


            if (
                button.classList.contains(
                    "view"
                )
            ) {

                openDetailModal(id);

            }


            if (
                button.classList.contains(
                    "edit"
                )
            ) {

                openEditModal(id);

            }


            if (
                button.classList.contains(
                    "delete"
                )
            ) {

                deleteProductId =
                    id;

                deleteModal.classList.add(
                    "show"
                );

            }

        }
    );


    document.getElementById(
        "confirmDelete"
    ).addEventListener(
        "click",
        deleteProduct
    );


    document.getElementById(
        "cancelDelete"
    ).addEventListener(
        "click",
        closeAllModals
    );


    document.getElementById(
        "addProductBtn"
    ).addEventListener(
        "click",
        openAddModal
    );


    document.getElementById(
        "closeModal"
    ).addEventListener(
        "click",
        closeAllModals
    );


    document.getElementById(
        "cancelModal"
    ).addEventListener(
        "click",
        closeAllModals
    );


    document.getElementById(
        "closeDetailModal"
    ).addEventListener(
        "click",
        closeAllModals
    );


    document.getElementById(
        "refreshBtn"
    ).addEventListener(
        "click",
        loadProducts
    );


    document.getElementById(
        "retryBtn"
    ).addEventListener(
        "click",
        loadProducts
    );


    searchInput.addEventListener(
        "input",
        () => {

            currentPage = 1;

            renderProducts();

        }
    );


    document.getElementById(
        "clearSearch"
    ).addEventListener(
        "click",
        () => {

            searchInput.value = "";

            currentPage = 1;

            renderProducts();

        }
    );


    categoryFilter.addEventListener(
        "change",
        () => {

            currentPage = 1;

            renderProducts();

        }
    );


    stockFilter.addEventListener(
        "change",
        () => {

            currentPage = 1;

            renderProducts();

        }
    );


    sortFilter.addEventListener(
        "change",
        () => {

            currentPage = 1;

            renderProducts();

        }
    );


    productModal.addEventListener(
        "click",
        event => {

            if (
                event.target ===
                productModal
            ) {

                closeAllModals();

            }

        }
    );


    detailModal.addEventListener(
        "click",
        event => {

            if (
                event.target ===
                detailModal
            ) {

                closeAllModals();

            }

        }
    );


    deleteModal.addEventListener(
        "click",
        event => {

            if (
                event.target ===
                deleteModal
            ) {

                closeAllModals();

            }

        }
    );


    // BẮT ĐẦU TẢI DỮ LIỆU TỪ DATABASE
    loadProducts();

});