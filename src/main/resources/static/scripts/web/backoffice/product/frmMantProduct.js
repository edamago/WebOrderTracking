$(document).on("click", "#btnagregar", function(){
    $("#hdidproduct").val("0");
    $("#txtdescripcion").val("");
    $("#txtum").val("");
    $("#txtstockmin").val("0");
    $("#txtstockmax").val("0");
    $("#txtpesob").val("0");
    $("#txtpeson").val("0");
    $("#txtalto").val("0");
    $("#txtancho").val("0");
    $("#txtprofundo").val("0");
    /*$("#cbodemanda").empty();
    $("#cbocomercial").empty();*/
    $("#txtcomentarios").val("");
    $("#swestado").hide();
    $("#cboestado").val("A");
    $("#modalNuevo").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#hdidproduct").val($(this).attr("data-productoid"));
    $("#txtdescripcion").val($(this).attr("data-producto_descripcion"));
    $("#txtum").val($(this).attr("data-unidad_medida"));
    $("#txtstockmin").val($(this).attr("data-stock_minimo"));
    $("#txtstockmax").val($(this).attr("data-stock_maximo"));
    $("#txtpesob").val($(this).attr("data-peso_bruto"));
    $("#txtpeson").val($(this).attr("data-peso_neto"));
    $("#txtalto").val($(this).attr("data-alto"));
    $("#txtancho").val($(this).attr("data-ancho"));
    $("#txtprofundo").val($(this).attr("data-profundo"));
    $("#cbodemanda").val($(this).attr("data-clasif_demanda"));
    $("#cbocomercial").val($(this).attr("data-clasif_comercial"));
    $("#txtcomentarios").val($(this).attr("data-comentarios"));
    $("#swestado").show();
    $("#cboestado").val($(this).attr("data-productoestado"));
    $("#modalNuevo").modal("show");
});

$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: "POST",
        url: "/backoffice/producto/guardar",
        contentType: "application/json",
        data: JSON.stringify({
            id: $("#hdidproduct").val(),
            descripcion: $("#txtdescripcion").val(),
            unidad_medida: $("#txtum").val(),
            stock_minimo: $("#txtstockmin").val(),
            stock_maximo: $("#txtstockmax").val(),
            peso_bruto: $("#txtpesob").val(),
            peso_neto: $("#txtpeson").val(),
            alto: $("#txtalto").val(),
            ancho: $("#txtancho").val(),
            profundo: $("#txtprofundo").val(),
            clasif_demanda: $("#cbodemanda").val(),
            clasif_comercial: $("#cbocomercial").val(),
            comentarios: $("#txtcomentarios").val(),
            estado: $("#cboestado").val(),
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarProductos()
                /*$("#tblusuario > tbody").html("");*/
            }
            alert(resultado.mensaje);
            $("#modalNuevo").modal("hide");
        }
    })
});

function listarProductos(){
    $.ajax({
        type: "GET",
        url: "/backoffice/producto/listar",
        dataType: "json",
        success: function(resultado){
            $("#tblproducto > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblproducto > tbody").append("<tr>"+
                    "<td>"+value.id+"</td>"+
                    "<td>"+value.descripcion+"</td>"+
                    "<td>"+value.unidad_medida+"</td>"+
                    "<td>"+value.stock_minimo+"</td>"+
                    "<td>"+value.stock_maximo+"</td>"+
                    "<td>"+value.peso_bruto+"</td>"+
                    "<td>"+value.peso_neto+"</td>"+
                    "<td>"+value.alto+"</td>"+
                    "<td>"+value.ancho+"</td>"+
                    "<td>"+value.profundo+"</td>"+
                    "<td>"+value.clasif_demanda+"</td>"+
                    "<td>"+value.clasif_comercial+"</td>"+
                    "<td>"+value.comentarios+"</td>"+
                    "<td>"+value.estado+"</td>"+
                    "<td>"+
                        "<button type='button' class='btn btn-info btnactualizar'" +
                            " data-productoid='"+value.id+"'"+
                            " data-producto_descripcion='"+value.descripcion+"'"+
                            " data-unidad_medida='"+value.unidad_medida+"'"+
                            " data-stock_minimo='"+value.stock_minimo+"'"+
                            " data-stock_maximo='"+value.stock_maximo+"'"+
                            " data-peso_bruto='"+value.peso_bruto+"'"+
                            " data-peso_neto='"+value.peso_neto+"'"+
                            " data-alto='"+value.alto+"'"+
                            " data-ancho='"+value.ancho+"'"+
                            " data-profundo='"+value.profundo+"'"+
                            " data-clasif_demanda='"+value.clasif_demanda+"'"+
                            " data-clasif_comercial='"+value.clasif_comercial+"'"+
                            " data-comentarios='"+value.comentarios+"'"+
                            " data-productoestado='"+value.estado+"'"+
                            "><i class='fas fa-edit'></i></button></td></tr>"
                );
            })
        }
    })
};

