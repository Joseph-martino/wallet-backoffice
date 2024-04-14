document.addEventListener('DOMContentLoaded', function() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'rest/wallet', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                var products = JSON.parse(xhr.responseText);
                // Appeler une fonction pour afficher les produits
                displayProducts(products);
            } else {
                console.error('Erreur lors de la récupération des données:', xhr.statusText);
            }
        }
    };
    xhr.send();
});

function displayProducts(products) {
    var productList = document.getElementById('productList');
    products.forEach(function(product) {
        var productItem = document.createElement('li');
        productItem.innerHTML = product.name + ' - ' + product.price;
        productList.appendChild(productItem);
    });
}



//var xhr = new XMLHttpRequest();
//xhr.open('GET', 'rest/portefeuille', true); // Spécifiez l'URL de votre servlet et le type de requête (GET ou POST)
//xhr.setRequestHeader('Content-Type', 'application/json'); // Définissez le type de contenu de la requête (ici, JSON)
//xhr.onreadystatechange = function() {
//    if (xhr.readyState === XMLHttpRequest.DONE) {
//        if (xhr.status === 200) {
//            // La requête a réussi
//            var products = JSON.parse(xhr.responseText);
//            products.forEach(function(product) {
//                // Mettre à jour les éléments HTML avec les données du produit
//                document.getElementById('productName').textContent = product.name;
//                document.getElementById('productPrice').textContent = product.price;
//            });
//            console.log('Données reçues:', products);
//            // Manipulez les données ici
//        } else {
//            // La requête a échoué
//            console.error('Erreur lors de la récupération des données:', xhr.statusText);
//        }
//    }
//};
//xhr.send(); // Envoyer la requête
/*
fetch('url_de_votre_servlet')
  .then(response => {
    if (!response.ok) {
      throw new Error('Erreur lors de la récupération des données');
    }
    return response.json(); // Parse la réponse JSON
  })
  .then(data => {
    // Les données ont été récupérées avec succès
    console.log('Données reçues:', data);
    // Manipulez les données ici
  })
  .catch(error => {
    // Une erreur s'est produite lors de la récupération des données
    console.error('Erreur:', error);
  });
*/