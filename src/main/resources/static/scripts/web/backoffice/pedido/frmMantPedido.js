$(document).ready(function () {
    $('#btnAgregarProducto').on('click', function () {

        var producto = $('#cboproducto option:selected').text();
        var productoId = $('#cboproducto').val();  // Captura el ID del producto
        //console.log('Producto seleccionado:', producto);
        var cantidad = $('#txtcantidad').val();
        var precio = $('#txtprecio').val();
        var comentarios = $('#txtcomentariosdet').val();

        if (!producto || !cantidad || !precio) {
            alert('Por favor, complete todos los campos.');
            return;
        }


        var newRow = $('<tr>');
        newRow.append('<td>' + productoId + '</td>');  // Puedes asignar un valor si es necesario
        newRow.append('<td>' + producto + '</td>');
        newRow.append('<td>' + cantidad + '</td>');
        newRow.append('<td>' + precio + '</td>');
        newRow.append('<td>' + comentarios + '</td>'); // Puedes asignar un valor si es necesario
        newRow.append('<td>A</td>'); // Puedes asignar un valor si es necesario



        $('#tbldetallepedido tbody').append(newRow);


        $('#cboproducto').val('');
        $('#txtcantidad').val('');
        $('#txtprecio').val('');
        $('#txtcomentariosdet').val('')
    });
});

//$(document).on("click", ".btnactualizar", function() {
//    // Cargar los valores del pedido a partir de los atributos data-* del botón
//    $("#hdidpedido").val($(this).attr("data-id"));
//    $("#txtfecha").val($(this).attr("data-fecha"));
//    $("#txtatencion").val($(this).attr("data-atencion"));
//    $("#cbomoneda").val($(this).attr("data-moneda"));
//    $("#txtcomentarios").val($(this).attr("data-comentario"));
//    $("#cboestado").val($(this).attr("data-estado"));

//    // Cargar cliente seleccionado en el select
//    var clienteId = $(this).attr("data-t_cliente_id");
//    var clienteNombre = $(this).attr("data-t_cliente_nombre");

 //   var cbocliente = $("#cbocliente");
 //   cbocliente.empty();  // Limpiar las opciones anteriores
 //   cbocliente.append($('<option>', {
 //       value: clienteId,
 //       text: clienteNombre,
 //       selected: true
 //   }));

 //   listarClientes(clienteId);  // Si es necesario, carga otros clientes para el select
 //   listarProductos(0);  // Si es necesario, carga los productos

 //   $("#modalNuevo").modal("show");
//});

//$(document).on("click", ".btnactualizar", function(){
    //$("#hdidcliente").val($(this).attr("data-id"));
    //$("#cbotipodoc").val($(this).attr("data-tipo_documento"));
    //$("#txtdocumento").val($(this).attr("data-documento"));
    //$("#txtnombre").val($(this).attr("data-nombre"));
    //$("#txtdireccion").val($(this).attr("data-direccion"));
    //$("#txtdireccione").val($(this).attr("data-direccion_entrega"));
    //$("#txtdistrito").val($(this).attr("data-distrito"));
    //$("#txtciudad").val($(this).attr("data-ciudad"));
    //$("#cbotipo").val($(this).attr("data-tipo"));
    //$("#cbocomercial").val($(this).attr("data-clasif_comercial"));
    //$("#txtcomentarios").val($(this).attr("data-comentarios"));
    //$("#swestado").show();
    //$("#cboestado").val($(this).attr("data-estado"));
    //listarClientes(0);
    //listarProductos(0);
    //$("#modalNuevo").modal("show");
//});
$(document).on("click", "#btnagregar", function(){
    $("#hdidpedido").val("0");
    var today = new Date().toISOString().split('T')[0];
    $("#txtfecha").val(today);

    //$("#cbotipodoc").val("R");
    //$("#txtdocumento").val("");
    //$("#txtnombre").val("");
    //$("#txtdireccion").val("");
    //$("#txtdireccione").val("");
    //$("#txtdistrito").val("");
    //$("#txtciudad").val("");
    //$("#cbotipo").val("C");
    //$("#cbocomercial").val("E");
    //$("#txtcomentarios").val("");
    //$("#swestado").hide();
    //$("#cboestado").val("A");
    listarClientes(0);
    listarProductos(0);
    $("#modalNuevo").modal("show");
});

$(document).on("click", "#btnguardar", function(){
    var detallePedidos = [];
    var subtotal = 0;

    $("#tbldetallepedido tbody tr").each(function() {
        var productoId = $(this).find("td:eq(0)").text(); // Columna Id
        var producto = $(this).find("td:eq(1)").text(); // Columna Producto
        var cantidad = parseFloat($(this).find("td:eq(2)").text()); // Columna Cantidad
        var precio = parseFloat($(this).find("td:eq(3)").text()); // Columna Precio
        var comentarios = $(this).find("td:eq(4)").text(); // Columna Comentarios
        var estado = $(this).find("td:eq(5)").text(); // Columna Estado

        // Mensajes de depuración
        //console.log('Id Producto:', productoId);
        //console.log('Producto:', producto);
        //console.log('Cantidad:', cantidad);
        //console.log('Precio:', precio);
        //console.log('Comentarios:', comentarios);
        //console.log('Estado:', estado);

        var detalle = {
            id: 0, // Aquí ya no usas id
            t_producto_id: productoId,
            cantidad: cantidad,
            precio: precio,
            comentarios: comentarios,
            estado: estado || "A"
        };
        detallePedidos.push(detalle);
        subtotal += cantidad * precio;
    });

    var igv = subtotal * 0.18; // Suponiendo un IGV del 18%
    var total = subtotal + igv;

    // Mensaje de depuración antes de la llamada AJAX
    //console.log('Subtotal:', subtotal);
    //console.log('IGV:', igv);
    //console.log('Total:', total);

    $.ajax({
        type: "POST",
        url: "/backoffice/pedido/guardar",
        contentType: "application/json",
        data: JSON.stringify({
            id: $("#hdidpedido").val(),
            t_cliente_id: $("#cbocliente").val(),
            fecha: $("#txtfecha").val(),
            atencion: $("#txtatencion").val(),
            moneda: $("#cbomoneda").val(),
            subtotal: subtotal,
            igv: igv,
            total: total,
            comentario: $("#txtcomentarios").val(),
            enviado: 0,
            estado: $("#cboestado").val(),
            detalles: detallePedidos // Añadir la lista de detalles
        }),
        success: function(resultado){
            console.log('Respuesta del servidor:', resultado);
            if(resultado.respuesta){
                listarDetalle()
                //$("#tbldetallepedido > tbody").html(""); // Limpiar tabla de detalle si es necesario
            }
            alert(resultado.mensaje);
            $("#modalNuevo").modal("hide");
        },
        error: function(jqXHR, textStatus, errorThrown) {
            // Mensaje de depuración en caso de error
            console.log('Error en la solicitud AJAX:', textStatus, errorThrown);
        }
    });
});



function listarClientes(idcliente){
    $.ajax({
        type: "GET",
        url: "/backoffice/cliente/listar",
        dataType: "json",
        success: function(resultado){
            $.each(resultado, function(index, value){
                $("#cbocliente").append(
                `<option value="${value.id}">${value.nombre}</option>`
                )
            })
            if(idcliente > 0) {
                $("#cbocliente").val(idcliente);
            }

        }
    })
}
function listarProductos(idproducto){
    $.ajax({
        type: "GET",
        url: "/backoffice/producto/listar",
        dataType: "json",
        success: function(resultado){
            $.each(resultado, function(index, value){
                $("#cboproducto").append(
                `<option value="${value.id}">${value.descripcion}</option>`
                )
            })
            if(idproducto > 0) {
                $("#cboproducto").val(idproducto);
            }

        }
    })
}

function listarPedidos(){
    $.ajax({
        type: "GET",
        url: "/backoffice/pedido/listar",
        dataType: "json",
        success: function(resultado){
            $("#tblpedido > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblpedido > tbody").append("<tr>"+
                    "<td>"+value.id+"</td>"+
                    "<td>"+value.tipo_documento+"</td>"+
                    "<td>"+value.documento+"</td>"+
                    "<td>"+value.nombre+"</td>"+
                    "<td>"+value.direccion+"</td>"+
                    "<td>"+value.direccion_entrega+"</td>"+
                    "<td>"+value.distrito+"</td>"+
                    "<td>"+value.ciudad+"</td>"+
                    "<td>"+value.tipo+"</td>"+
                    "<td>"+value.clasif_comercial+"</td>"+
                    "<td>"+value.comentarios+"</td>"+
                    "<td>"+value.estado+"</td>"+
                    "<td>"+
                        "<button type='button' class='btn btn-info btnactualizar'" +
                            " data-id='"+value.id+"'"+
                            " data-tipo_documento='"+value.tipo_documento+"'"+
                            " data-documento='"+value.documento+"'"+
                            " data-nombre='"+value.nombre+"'"+
                            " data-direccion='"+value.direccion+"'"+
                            " data-direccion_entrega='"+value.direccion_entrega+"'"+
                            " data-distrito='"+value.distrito+"'"+
                            " data-ciudad='"+value.ciudad+"'"+
                            " data-tipo='"+value.tipo+"'"+
                            " data-clasif_comercial='"+value.clasif_comercial+"'"+
                            " data-comentarios='"+value.comentarios+"'"+
                            " data-estado='"+value.estado+"'"+
                            "><i class='fas fa-edit'></i></button></td></tr>"
                );
            })
        }
    })
};
function listarDetalle(){
    $.ajax({
        type: "GET",
        url: "/backoffice/pedido/listar",
        dataType: "json",
        success: function(resultado){
            $("#tblpedido > tbody").html("");
            $.each(resultado, function(index, value){

                const checkboxEnviado = value.enviado ? "<i class='fas fa-check-square'></i>" : "<i class='far fa-square'></i>";

                $("#tblpedido > tbody").append("<tr>"+
                    "<td>"+value.id+"</td>"+
                    "<td>"+value.fecha+"</td>"+
                    "<td>"+value.atencion+"</td>"+
                    "<td>"+value.moneda+"</td>"+
                    "<td>"+value.subtotal+"</td>"+
                    "<td>"+value.igv+"</td>"+
                    "<td>"+value.total+"</td>"+
                    "<td>"+value.comentario+"</td>"+
                    "<td>"+checkboxEnviado+"</td>"+
                    "<td>"+value.estado+"</td>"+
                    "<td>"+value.cliente.id+"</td>"+
                    "<td>"+value.cliente.nombre+"</td>"+
                    "<<td>"+
                        "<button type='button' class='btn btn-info btnactualizar'" +
                            " data-id='"+value.id+"'"+
                            " data-fecha='"+value.fecha+"'"+
                            " data-comentario='"+value.atencion+"'"+
                            " data-enviado='"+value.moneda+"'"+
                            " data-idestado='"+value.subtotal+"'"+
                            " data-nombreestado='"+value.igv+"'"+
                            " data-iddetalle='"+value.total+"'"+
                            " data-idproducto='"+value.comentario+"'"+
                            " data-nombreproducto='"+value.enviado+"'"+
                            " data-idcliente='"+value.estado+"'"+
                            " data-nombrecliente='"+value.cliente.id+"'"+
                            "><i class='fas fa-edit'></i></button></td></tr>>"
                );
            })
        }
    })
};
