document.addEventListener('DOMContentLoaded', () => {
    const getAllItems = document.getElementById("get-all-items"),
          addForm = document.getElementById("add-data-form"),
          updateForm = document.getElementById("update-data-form"),
          deleteForm = document.getElementById("delete-data-form");

    if(getAllItems) getAllItems.addEventListener("click", fetchAllData);
    if(addForm) addForm.addEventListener("submit", addItem);
    if(updateForm) updateForm.addEventListener("submit", updateForm);
    if(deleteForm) deleteForm.addEventListener("submit", deleteItem);
})

// Fetch All Data
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
                    <td>${item.thumbnail}</td>
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

// Add Item
function addItem(event) {
    event.preventDefault(); // Prevent form submission
    
    const thumbnail = document.getElementById("thumbnail-input");
    const asin = document.getElementById("asin-txt-input").value;
    const name = document.getElementById("name-txt-input").value;
    const ingredients = document.getElementById("ingredients-txt-input").value;

    const newItem = { thumbnail:thumbnail, asin: asin, name: name, ingredients: ingredients };

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
    document.getElementById("add-data-form").reset();
}

// Update Item
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
    document.getElementById("update-data-form").reset();
}

// Update Item
function deleteItem(event) {
    event.preventDefault(); // Prevent form submission    <input type="text" placeholder="Search by ASIN or ID...">

    const id = document.getElementById("deleteById").value;

    fetch(`/hazfinder/api/items/${id}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        }
    })
    .then(response => response.json())
    .then(data => {
        console.log("Item deleted:", data);
        fetchAllData();
    })
    .catch(error => {
        console.error("Error deleting item:", error);
    });

    // Clear form fields
    document.getElementById("delete-data-form").reset();
}