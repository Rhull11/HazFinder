document.getElementById("fetchAllData").addEventListener("click", fetchAllData);
//document.getElementById("dataForm").addEventListener("submit", addItem);
document.getElementById("update-form").addEventListener("submit", updateItem);

function fetchAllData() {
    fetch("/hazfinder/api/items")
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#dataTable tbody");
            tableBody.innerHTML = "";

            data.forEach(item => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${item.id}</td>
                    <td>${item.asin}</td>
                    <td>${item.name}</td>
                    <td>${item.ingredients}</td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error("Error fetching data: ", error);
        });
}

function addItem(event) {
    event.preventDefault(); // Prevent form submission

    const asin = document.getElementById("asin-txt-input").value;
    const name = document.getElementById("name-createItem.html.html-input").value;
    const ingredients = document.getElementById("ingredients-txt-input").value;

    const newItem = { asin: asin, name: name, ingredients: ingredients };

    fetch("/hazfinder/api/items", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(newItem)
    })
    .catch(error => {
        console.error("Error adding item:", error);
    });

    // Clear form fields
    document.getElementById("dataForm").reset();
}

function updateItem(event) {
    event.preventDefault(); // Prevent form submission    <input type="text" placeholder="Search by ASIN or ID...">

    const id = document.getElementById("updateId").value;
    const asin = document.getElementById("updateAsin").value;
    const name = document.getElementById("updateName").value;
    const ingredients = document.getElementById("updateIngredients").value;

    const updatedItem = { id: parseInt(id), asin: asin, name: name, ingredients: ingredients };

    fetch(`/hazfinder/api/items/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(updatedItem)
    })
    .then(response => response.json())
    .then(data => {
        console.log("Item updated:", data);
        fetchAllData();
    })
    .catch(error => {
        console.error("Error updating item:", error);
    });

    // Clear form fields
    document.getElementById("update-form").reset();
}