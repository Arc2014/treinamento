var lista;
var agenda
var id;


function carregarEventos(){
	$.ajax({
		url: "http://localhost:8080/box-web/rest/evento",			 
		type: "GET",
		dataType : "json",			 
		success: function( json ) {
			lista = json;
			$(json).each(function(key, agenda){
				var inicio = moment(agenda.dataInicio).format('DD MM YYYY, HH:mm');
				var fim = moment(agenda.dataFim).format('DD MM YYYY, HH:mm');
				$( "#eventos" ).append('<tr id="idAgenda'+ agenda.id +'"><td>' + agenda.tituloEvento + '</td><td>' + agenda.descricaoEvento + 
						'</td><td>'	+ inicio + '</td><td>' + fim + 
						'</td><td>' + agenda.recurso.descricao + '</td><td>' + agenda.pessoa.nome + 
						'<td><a onclick="deletarEvento(' + agenda.id +')"><img alt="deletar" width="40" height="40" src="resoruces/img/Trash.png"></a></td>' + 
						'<td><a onclick="preparaAlterar(' + key +')"><img alt="editar" width="40" height="40" src="resoruces/img/editar.jpg"></a></td></tr>');
			});
		},
		error: function( xhr, status, errorThrown ) {
			alert(xhr.responseText);
		}
	});
	
}

/*'<td><a href="cadastrar.html?id='+agenda.id+'"><img alt="editar" width="40" height="40" src="resoruces/img/editar.jpg"></a></td></tr>');*/

function deletarEvento(id){
	
	$.ajax({
		url: "http://localhost:8080/box-web/rest/evento/" + id,			 
		type: "DELETE",
		dataType : "json",

		success: function( json ) {
			$("#idAgenda"+id).hide('slow').remove();
		},
		error: function( xhr, status, errorThrown ) {
			alert(xhr.responseText);

		}
	});
};

function getEventoPorId(){	
	var id = document.getElementById('eventoConsultar').value;
	if (!id == ""){
		$.ajax({
			url: "http://localhost:8080/box-web/rest/evento/" + id,			 
			type: "GET",
			dataType : "json",	
			success: function( agenda ) {			
				var inicio = moment(agenda.dataInicio).format('DD MM YYYY, HH:mm');
				var fim = moment(agenda.dataFim).format('DD MM YYYY, HH:mm');				
					$("#eventos").find("td").remove();						
					$( "#eventos" ).append('<tr><td>' + agenda.tituloEvento + '</td><td>' + agenda.descricaoEvento + 
							'</td><td>'	+ inicio + '</td><td>' + fim + 
							'</td><td>' + agenda.recurso.descricao + '</td><td>' + agenda.pessoa.nome + 
							'<td><a onclick="deletaEvento(' + agenda.id +')"><img alt="deletar" width="40" height="40" src="resoruces/img/Trash.png"></a></td></tr>');
				},
				
			error: function( xhr, status, errorThrown ) {
				alert(xhr.responseText);
	
			}		
		});
	}else{
		
		$(lista).each(function(key, agenda){
			var inicio = moment(agenda.dataInicio).format('DD MM YYYY, HH:mm');
			var fim = moment(agenda.dataFim).format('DD MM YYYY, HH:mm');
			$( "#eventos" ).append('<tr id="idAgenda'+ agenda.id +'"><td>' + agenda.tituloEvento + '</td><td>' + agenda.descricaoEvento + 
					'</td><td>'	+ inicio + '</td><td>' + fim + 
					'</td><td>' + agenda.recurso.descricao + '</td><td>' + agenda.pessoa.nome + 
					'<td><a onclick="deletarEvento(' + agenda.id +')"><img alt="deletar" width="40" height="40" src="resoruces/img/Trash.png"></a></td></tr>');
		});
		
		
	}
}	

function salvarEvento(){
	
	evento = JSON.stringify({
			id : window.name,
			tituloEvento : $("#tituloEvento").val(),
			descricaoEvento : $("#descricaoEvento").val(),
			dataInicio : moment($('#dataInicio').val(), 'DD.MM.YYYY HH:mm').toDate(),
			dataFim : moment($('#dataFim').val(), 'DD.MM.YYYY HH:mm').toDate(),
			pessoa : {
				id: $("#solicitante").val()	
			},
			recurso : {
				id: $("#recurso").val()
			}
		});
		
		if (window.name == ""){
			gravar(evento);
		}else{
			alterar(evento);
		}

	}

	function gravar(evento){$.ajax({
		url: "http://localhost:8080/box-web/rest/evento",			 
		type: "PUT",
		dataType : "json",
		contentType : "application/json",
		data : evento,
	
		success: function( json ) {
			location.href="index.html"
		},
		error: function( xhr, status, errorThrown ) {
			console.dir( xhr.responseText );
		}
	});}
	
	function alterar(evento){$.ajax({
		url: "http://localhost:8080/box-web/rest/evento",			 
		type: "POST",
		dataType : "json",
		contentType : "application/json",
		data : evento,
	
		success: function( json ) {
			location.href="index.html"
		},
		error: function( xhr, status, errorThrown ) {
			console.dir( xhr.responseText );
		}
	});}

function preparaAlterar(key) {	
	agenda = lista[key]; 
	window.name = agenda.id;
	location.href="cadastrar.html"
}

function carregarTelaALterar(){
	$.ajax({
		url: "http://localhost:8080/box-web/rest/evento/" + window.name,			 
		type: "GET",
		dataType : "json",	
		success: function( agenda ) {			
			$("#tituloEvento").val(agenda.tituloEvento),
			$("#descricaoEvento").val(agenda.descricaoEvento),
			$('#dataInicio').val(agenda.dataInicio),
			$('#dataFim').val(agenda.dataFim);
			},
			
		error: function( xhr, status, errorThrown ) {
			alert(xhr.responseText);

		}		
	});
	
}
