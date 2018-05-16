<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ page import="model.TipologiaUtente"%>
<%@ page import="bean.IndirizzoSpedizioneBean"%>
<%@ page import="bean.AnnuncioBean"%>   
<jsp:useBean id="annuncioBean" scope="request" class="bean.AnnuncioBean" />
<jsp:useBean id="gestioneIndirizzoSpedizioneBean" scope="request" class="bean.GestioneIndirizzoSpedizioneBean" />
<!-- <jsp:useBean id="annuncioBean2" scope="session" class="bean.AnnuncioBean" />-->
<jsp:useBean id="loginBean" scope="session" class="bean.LoginBean" />


<%	if(request.getParameter("idAnnuncio") == null)
	{ 
	%>
		<jsp:forward page="Login.jsp"/> 
	<%
	}


	if(loginBean.userIsNotLogged())	
	{ 
	%>
		<jsp:forward page="Login.jsp"> 
			<jsp:param name="redirectDettaglioAnnuncio" value="true" /> 
		</jsp:forward> 	
		
	<%	
	}

	else
	{
		if(request.getParameter("effettuaAcquisto") != null)
		{
			
			//request.setAttribute("ciao", annuncioBean);
	            
			//RequestDispatcher rd = request.getRequestDispatcher("EffettuaAcquisto.jsp");
			  
            //rd.forward(request,response);

		}
		
		else
		{
		
	%>
	

<!-- HTML 5 -->
<!DOCTYPE html>
<html><head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>I tuoi Annunci</title>

    <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="./css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./css/jumbotron-narrow.css" rel="stylesheet">
    <link href="./css/bootstrap-datepicker3.min.css" rel="stylesheet">

   
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>

 /* mixin for multiline */
    .block-with-text {
      overflow: hidden;
      position: relative;
      line-height: 1.2em;
      max-height: 2.4em;
      text-align: justify;
      margin-right: -1em;
      padding-right: 1em;
    }

    .block-with-text:before {
        content: '...';
        position: absolute;
        right: 0;
        bottom: 0;
    }

    .block-with-text:after {
        content: '';
        position: absolute;
        right: 0;
        width: 1em;
        height: 1em;
        margin-top: 0.2em;
        background: white;
    }





    @media (min-width: 768px){
.container {
    max-width: 970px !important; 
        }
    }
    .card {
    position: relative;
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    -webkit-flex-direction: column;
    -ms-flex-direction: column;
    flex-direction: column;
    background-color: #fff;
    border: 1px solid rgba(0,0,0,.125);
    border-radius: .25rem;
}

.card-block {
    -webkit-box-flex: 1;
    -webkit-flex: 1 1 auto;
    -ms-flex: 1 1 auto;
    flex: 1 1 auto;
    padding: 1.25rem;
}

.card-title {
    margin-bottom: .75rem;
}

.card-img-top {
    border-top-right-radius: calc(.25rem - 1px);
    border-top-left-radius: calc(.25rem - 1px);
}
    </style>

</head>

   <body style="
    font-family: -apple-system,system-ui,BlinkMacSystemFont,&quot;Segoe UI&quot;,Roboto,&quot;Helvetica Neue&quot;,Arial,sans-serif;
">

    <div class="container">
  <jsp:include page="header.jsp"/>

      <div class="jumbotron-fluid">
      
     
				<div class="row" style="margin-bottom: 2em;">


      						<!-- PRIMO ELEMENTO -->
							  <div class="col-sm-12">
							  
							  <%
							  		if(loginBean.getTipo().equals(TipologiaUtente.PRODUTTORE))
							  		{
							  			%>	
						  				
						  				<div class="alert alert-warning" role="alert">
						  	        	  <strong>Operazione non consentita:</strong> il tuo account di Produttore non ti permette di finalizzare la richiesta. 
						  	        	  <br>
						  	        	  <br>Se vuoi visualizzare i dettagli dell'annuncio per procedere all'acquisto registrati come Consumatore.
						  	       		</div>
						  				
						  			<%	
							  			
							  		}
							  
							  		else
							  		{		
							  			int idAnnuncio = annuncioBean.parseRequest((request.getParameter("idAnnuncio")));
						  				
						  				if(idAnnuncio < 0)
						  				{
						  				%>
						  					<jsp:forward page="index.jsp" />
						  				<%	
						  				}
							  	
					  					annuncioBean.popolaAnnuncioPerLettura(idAnnuncio);
					  					if(!annuncioBean.popolaAnnuncioPerLettura(idAnnuncio))
					  					{
					  						%>	
					  						<div class="alert alert-danger" role="alert">
					  			        	  <strong>Errore nel trovare l'annuncio</strong> 
					  			       		</div>
					  						
					  						<%	
					  					
					  					}
					  					
					  					else
					  					{
					  					
 
									  %>
							  
									  
															    <div class="media" style="
								    background-color: #eee;
								    border-top-left-radius: 0.8rem;
								    border-top-right-radius: 0.8rem;
								    border-bottom-right-radius: 0.8rem;
								    border-bottom-left-radius: 0.8rem;
								">
								
								
								<div class="row" style="
									    margin-top: 1.5em;
									    padding-right: 2em;
									    padding-bottom: 2em;
									"><div class="col-sm-8"></div>
									
									
									
									<div class="col-sm-4" style=""><input type="text" class="form-control" id="categoria" name="categoria" readonly="" value="<%
									out.print(annuncioBean.getCategoria().toUpperCase()); %>" style="
									    background-color: white;
									    text-align: center;
									    letter-spacing: 2px;
									    font-size: 0.9em;
									    font-weight: 700;
									    font-style: italic;
									">
									</div></div>
								
								
								  <img class="img-thumbnail img-responsive d-flex align-self-start mr-3" src="<%out.print(annuncioBean.getFoto()); %>" alt="Generic placeholder image" style="
								    margin: 0 auto;
								    justify-content: center;
								    display: flex;
								    padding-top: 0.5em;
								    margin-top: 1.5em;
								    width: auto;
								    max-height: 420px;
								    max-width: 550px;
								">
								  <div class="media-body" style="
								    padding-left: 3em;
								    padding-right: 3em; padding-bottom: 3em;
								">
								   	<input type="text" id="titolo" name="titolo" class="form-control" readonly placeholder="<%out.print(annuncioBean.getTitolo()); %>"value="<%out.print(annuncioBean.getTitolo()); %>" style="
								    background-color: white;
								    font-size: 25px;
								    padding: 1em;
								    margin-top: 1em;"> 
								    
								    <textarea class="form-control textarea" rows="10" name="descrizione" id="descrizione" readonly style="background-color: white; margin-top: 6.99074px;padding: 2em 1em 1em;height: 224px;margin-bottom: 0px;font-style: oblique;">
    <%out.print(annuncioBean.getDescrizione()); %>
    </textarea>
								    
								  
								    <div class="row" style="
								    margin-top: 1.5em;
								">
								  
								
								<div class="col-sm-2">
								         <input type="text" class="form-control" id="prezzoLabel" name="prezzoLabel" readonly value="Prezzo :" style="background-color: white;
								    font-weight: 700;
								">
								        </div><div class="col-sm-2">
								         <input type="text" class="form-control" id="prezzo" name="prezzo" readonly value="<%out.print(annuncioBean.getPrezzo()); %>" style="background-color: white;
								    text-align: center;
								">
								        </div>
								         
								</div>
								
								<div class="row" style="
								    margin-top: 0.5em;
								">
								  
								
								<div class="col-sm-2">
								         <input type="text" class="form-control" id="quantit‡Label" name="quantit‡Label" readonly="" value="Disponibilit‡†:" style="background-color: white;
								    font-weight: 700;
								">
								        </div><div class="col-sm-2">
								         <input type="text" class="form-control" id="quantit‡" name="quantit‡" readonly="" value="<%out.print(annuncioBean.getQuantit‡()); %>" style="background-color: white;
								    text-align: center;
								">
								        </div>
								         
								</div>
								
								<br><br>
								<div class="row" style="
								    margin-top: 5em;
								    /* width: 80%; */
								    display: flex;
								    justify-content: center;
								">
								  
								
								<div class="col-sm-1"></div>
								
								<div class="col-sm-6">
								         <button id="elimina" name="elimina" class="btn btn-lg btn-success btn-block center-block" data-toggle="modal" data-target="#modalCompra">Compra</button>
								
								<div class="modal fade" id="modalCompra" tabindex="-1" role="dialog" aria-labelledby="modalCompra" aria-hidden="true" style="display: none;">
								 <div class="modal-dialog modal-lg" role="document">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModal3Label" style="
								    font-size: 2.5em;
								    text-align: center;
								    font-weight: 300;
								">Modulo acquisto</h5>
								        
								      </div>
								      
								      
<div class="modal-footer">
<p class="lead" style="
    text-align: center;
    font-size: 1em;
">La transazione verr‡ processata mediante servizio esterno erogato dalla societ‡ Tal dei tali.
<br>
Nessun dato personale sar‡ conservato da Portale Annunci.
</p>
								      <div class="form-group" style="
    padding-left: 6em;
    padding-right: 6em;
    padding-bottom: 2em;
    padding-top: 2em;
">
				    <div class="row" style="">
				    <form class="form-horizontal" action="EffettuaAcquisto.jsp?idAnnuncio=<%out.print(annuncioBean.getId()); %>" name="myform" method="POST">
				    
				    
				<%
				    
				  	int result = gestioneIndirizzoSpedizioneBean.cercaIndirizzi(loginBean.getId());
				    
					boolean formDisabled = false;
					if(result == 0)
						formDisabled = true;

				    
				%>
 
				  <div class="col-sm-6">
				  
				         <label class="control-label" for="nominativo" style="text-align: left;float: left;">Nominativo completo</label>
				        </div>
				         <div class="col-sm-6">
				         
				        </div>
				</div>
				       <div class="row" style="
				    margin-top: 0.5em;
				">
				  <div class="col-sm-12">
				         <input type="text" id="nominativo" name="nominativo" class="form-control" placeholder="Nome e Cognome" required="" autofocus="" <%
				         out.print(formDisabled ? "disabled" : "");%>>
				        </div>
				         
				</div>
				    

				<div class="row" style="margin-top: 1.5em;">
				  <div class="col-sm-7">
				         <label class="control-label" for="numeroCarta" style="text-align: left;float: left;">Carta</label>
				        </div>
				
				<div class="col-sm-3" style="
    text-align: center;
">
				         <label class="control-label" for="scadenza" style="text-align: center;/* float: left; */">Scadenza</label>
				        </div>
				
				<div class="col-sm-2" style="
    text-align: center;
">
				         <label class="control-label" for="cvv" style="text-align: center;/* float: left; */">CVV/CVV2</label>
				        </div>
				         
				</div>
				    <div class="row" style="margin-top: 0.5em;">
				  <div class="col-sm-7">
				         <input type="text" id="numeroCarta" name="numeroCarta" class="form-control" placeholder="Numero carta di Credito" required="" <%
				         out.print(formDisabled ? "disabled" : "");%>>
				        </div>
				    <div class="col-sm-3">
				         <input type="text"  class="form-control" id="scadenza" name="scadenza" placeholder="MM/YYYY" required=""  <%
				         out.print(formDisabled ? "disabled" : "");%> style="text-align: center;">
      

				        </div>
				<div class="col-sm-2">
				         <input type="text" id="cvv" name="cvv" class="form-control" placeholder="---" required="" maxlength="3" style="
				    text-align: center;" <%
				         out.print(formDisabled ? "disabled" : "");%>>
				        </div>
				         
				</div><!--FINE PASSWORD-->
				

				
				<div class="row" style="margin-top: 5em;">
				  <div class="col-sm-6">
				         <label class="control-label" for="quantit‡DaAcquistare" style="text-align: left;float: left;">Inserisci Quantit‡</label>
				        </div>
				         
				</div>
				<div class="row" style="margin-top: 0.5em;">
				  <div class="col-sm-9">
				         <input type="number" onkeypress="return isNumeric(event)" oninput="maxLengthCheck(this)" id="quantit‡DaAcquistare" name="quantit‡DaAcquistare" class="form-control" placeholder="[1,<%
				         out.print(annuncioBean.getQuantit‡() + "]");%>" <%out.print(formDisabled ? "disabled" : "");%> min="1" max="<%
				         out.print(annuncioBean.getQuantit‡());%>" required>
				        </div>
				         
				</div>
				
				<% if(formDisabled)
				{
					%>	
	  				
	  				<div class="alert alert-danger" role="alert" style="margin-top: 4em;text-align: center;padding: 2em 0em;">
	  	        	  <strong>Errore:</strong> Non hai associato alcun indirizzo di spedizione. <a href="AggiungiIndirizzoSpedizione.jsp" class="alert-link">Crearne uno prima di procedere all'acquisto.</a>
   					</div>
	  				
	  				<%	
					
				}	
				
				else
				{
					
				%>			

						<div class="row" style="margin-top: 3em;">
										  <div class="col-sm-6">
										         <label class="control-label" for="idIndirizzoSpedizione" style="text-align: left;float: left;">Indirizzo Spedizione</label>
										        </div>
										         
										</div>
						<div class="row" style="margin-top: 0.5em;">
										  <div class="col-sm-9">
										         <select class="form-control" id="idIndirizzoSpedizione" name="idIndirizzoSpedizione">
							<%
								int i = 1;
								for(IndirizzoSpedizioneBean indirizzo : gestioneIndirizzoSpedizioneBean.getRisultati())
					    		{	
					    	%>	
								<option value="<%out.print(indirizzo.getId()); %>" ><%out.print("#" + i + ":  " + indirizzo.getNominativo() + 
					    			(indirizzo.getPresso().trim().isEmpty() ? "" : " c\\o " + indirizzo.getPresso()) + ", " + indirizzo.getVia() + ", " + indirizzo.getCitt‡() + " " + 
										indirizzo.getCap() + ", " + indirizzo.getProvincia()); %></option>
							<%
								i++;
					    		}
							%>

						  </select>
										        </div>
										         
										</div>
				
				<div class="row" style="margin-top: 8em;">
								  
				<div class="col-sm-6" style="
				    text-align: center;
				">
								         <p style="
				    text-align: left;
				    font-size: 2.5em;
				    letter-spacing: 10px;
				    font-weight: 300;
				">TOTALE</p>
								        </div><div class="col-sm-6">
								        <p style="
				    text-align: right;
				    font-size: 2.5em;
				    letter-spacing: 10px;
				    font-weight: 500;
				"><span id="totale">0.00</span> <span class="glyphicon glyphicon-euro" style="
				    font-size: x-large;
				    top: 0;
				"></span></p>
								        </div>
								         
								</div>
								
								
				<%
				}
				%>
				
				
				</div>
								      </div>
								      
				<%
					if(!formDisabled)
					{
				%>
						<div class="" style="
						    padding-bottom: 2.6em;
						    text-align: center;
						">
						    
						    <div class="row" style="display: flex;justify-content: center;">
						  <div class="col-sm-4" style="
						    margin-right: 1em;
						">
						<input type="hidden" id="versioneAnnuncio" name="versioneAnnuncio" value="<%out.print(annuncioBean.getUltimaModifica()); %>">
							<button name="reset" type="reset" value="reset" class="btn btn-danger btn-lg" style="display: block;width: 100%;padding: 0.5em 0 0.5em 0;" data-dismiss="modal">
						    	<span class="glyphicon glyphicon-remove"></span> Annulla
							</button>
						 </div>
						   
							<div class="col-sm-4" style="
						    margin-left: 1em;
						">
						 		
									<button type="submit" class="btn btn-lg btn-success btn-block center-block" 
									style="display: block;width: 100%;padding: 0.5em 0 0.5em 0;" name="effettuaAcquisto">Compra
						        </button></form>
						    </div>
						    
						  </div>
						    
														      
							</div>
								      
					<%
					
					}
					
					%>	      
								      
								      
						    </div>
						  </div>
						</div>
						        </div>
								
								<div class="col-sm-1"></div>
								         
								       
						         
						</div>
						    
						    
						  </div>
						</div>

<%
							  			}
					  					
							  		}

%>
							  </div>

      			

      						<!-- PRIMO ELEMENTO -->
							  

      			

      						<!-- PRIMO ELEMENTO -->
							  

      			</div>
      
      


      </div>
      
     

      

      <footer class="footer">
        <%@include file="footer.html"%>
      </footer>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./js/ie10-viewport-bug-workaround.js"></script>
     <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/bootstrap-datepicker.min.js"></script>
  <script>
  function maxLengthCheck(object) {
    if (object.value.length > object.max.length)
      object.value = object.value.slice(0, object.max.length)
      
    var prezzo = Number(document.getElementById("prezzo").value);
    var quantit‡DaAcquistare = Number(document.getElementById("quantit‡DaAcquistare").value);
    document.getElementById("totale").textContent= (quantit‡DaAcquistare * prezzo).toFixed(2);
  }
    
  function isNumeric (evt) {
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode (key);
    var regex = /[0-9]|\./;
    if ( !regex.test(key) ) {
      theEvent.returnValue = false;
      if(theEvent.preventDefault) theEvent.preventDefault();
    }
  }
</script>

<script type="text/javascript">
$( document ).ready(function() {
    $("#scadenza").datepicker({ 
        format: 'mm/yyyy',
        startDate: new Date(),
        minViewMode: 1,
        language: "it"
    });
   
}); 
</script>     

</body>
</html>

<% 
	} 

}
%>
