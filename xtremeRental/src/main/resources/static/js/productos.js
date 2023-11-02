// Arreglo para almacenar los pacientes
console.log("Me estoy ejecutando")

// Función para agregar un producto
function agregarProducto(event) {
    event.preventDefault();

    // Obtener los valores del formulario
    let nombreProducto = document.getElementById("nombre").value;
    let descripcionProducto = document.getElementById("descripcion").value;
    let stock = document.getElementById("stock").value;
    let precioPorHora = document.getElementById("precio").value;
    let imagen = document.getElementById("imagen").value;

    // Crear objeto producto
    const producto = {
        nombreProducto: nombreProducto,
        descripcionProducto: descripcionProducto,
        stock: stock,
        precioPorHora: precioPorHora,
        imagen: imagen
    };
    
    let error="";

    const url= '/productos';
    const settings ={
        "method":"POST",
        "headers":{
            'Content-Type': 'application/json',
        },
        "body":JSON.stringify(producto)
    }

    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        window.alert("Producto agregado correctamente")
        resetUploadForm();

    })    
    .catch(function (e){alert(e);      
    })
    console.log(error.getMessage);
    console.log(error);
    
} 

document.getElementById("altaForm").addEventListener("submit", agregarProducto);

function agregarImagen(event) {
    event.preventDefault();

    // Obtener los valores del formulario
    let imagen = document.getElementById("imagen").value;

    // Crear objeto producto
    const imagenes = {
        imagen: imagen
    };
    
    let error="";

    const url= 'assets/upload';
    const settings ={
        "method":"POST",
        "headers":{
            'Content-Type': 'application/json',
        },
        "body":JSON.stringify(imagenes)
    }

    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        window.alert("Producto agregado correctamente")
        resetUploadForm();

    })    
    .catch(function (e){alert(e);      
    })
    console.log(error.getMessage);
    console.log(error);
    
} 

document.getElementById("altaForm").addEventListener("submit", agregarImagen);


function resetUploadForm(){
    document.getElementById("nombre").value="";
    document.getElementById("descripcion").value="";
    document.getElementById("stock").value=null;
    document.getElementById("precio").value=null;
    document.getElementById("imagen").value="";
}