<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<%@ page import="model.TipologiaUtente"%> 
<jsp:useBean id="indirizzoSpedizioneBean" scope="request" class="bean.IndirizzoSpedizioneBean" />
<jsp:useBean id="loginBean" scope="session" class="bean.LoginBean" />
<jsp:setProperty name="indirizzoSpedizioneBean" property="*" />

<%	if(loginBean.userIsNotLogged() || !(loginBean.getTipo().equals(TipologiaUtente.CONSUMATORE)))	
	{ 
		response.sendRedirect("./Login.jsp");	
	}
%> 
   
    
    
<!DOCTYPE html>
<html><head>
  <title>Aggiungi Indirizzo Spedizione</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="./css/jumbotron-narrow.css" rel="stylesheet">
<style>


	.noHover:hover {
  		background-color: #e1a92f !important;
}

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




    /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
    .row.content {height: 1500px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      /*background-color: #555;
      color: white;*/
      padding: 15px;
      list-style: none;
    }
    
    footer li {
	  border-left: solid 1px;
	  display: inline-block;
	  line-height: 1em;
	  margin-left: 1em;
	  padding: 0 0 0 1em;
	}
	
	footer li:first-child {
	  border-left: 0;
	  margin-left: 0;
	  padding: 0;
	}
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height: auto;} 
    }
  </style>
</head>
<body style="
    font-family: -apple-system,system-ui,BlinkMacSystemFont,&quot;Segoe UI&quot;,Roboto,&quot;Helvetica Neue&quot;,Arial,sans-serif;
">
    <div class="container-fluid">
<jsp:include page="header.jsp"/>
    
    </div>


<div class="container-fluid">
<div class="row content" style="
    /* background-color: #f1f1f1; */
    height: 100%;
    margin-left: 3.5em;
    margin-right: 3.5em;
    padding-top: 0.5em;
    width: 70%;
">
    



<div class="col-sm-3 sidenav" style="background-color: transparent;"><br>
<br>

<br>


<ul class="list-group">
  	<li class="list-group-item active" style="text-align: center;font-weight: 400;font-size: 1.2em;">I tuoi indirizzi</li>
 	<li class="list-group-item"><a href="GestioneIndirizziSpedizione.jsp">Visualizza Indirizzi</a></li>
  	<li class="list-group-item"><a href="AggiungiIndirizzoSpedizione.jsp"> Nuovo Indirizzo</a></li>
</ul>
    </div>

    <div class="col-sm-1"></div>
<div class="col-sm-7" style="
    /*background-color: rgba(60, 181, 213, 0.84);*/
    /* height: inherit; */
    /* overflow-y: auto; */
">
  <div class="jumbotron">
  
  <%
  
  		if (request.getParameter("creaIndirizzo") != null)
  		{
  			
  			if(indirizzoSpedizioneBean.validate())
  			{
  				
  				
  				if(indirizzoSpedizioneBean.creaIndirizzo(loginBean.getId()))
				{
			
  				%>
              	   	<div class="alert alert-success" role="alert">
					  <strong>Operazione eseguita !</strong>
					  <a href="GestioneIndirizziSpedizione.jsp" class="alert-link">Clicca qui per vedere i tuoi indirizzi.</a>
					  
					</div>
              	<%
				}
  				else
  				{
				%>
	        	   	<div class="alert alert-danger" role="alert">
	        	          <strong>Errore</strong> nella creazione del tuo indirizzo di spedizione. Riprovare.
	        	    </div>
	        	<%	
  				}
		
 	
  			}
  			else
  			{
  			%>
        	   	<div class="alert alert-warning" role="alert">
        	          <strong>Attenzione:</strong> Uno dei campi è vuoto o contiene un valore non consistente. Riprova
        	    </div>
        	<%
	
  			}
  				
	
  		}
  
  		else
  		{
  		
  		%>
        <blockquote style="border-left: 5px solid #1070ea;">
		    		<p style="font-weight: 300;font-size: 1.3em;">Aggiungi un nuovo indirizzo di spedizione</p>
		    		<footer style="background-color: transparent;">Tutti i campi sono obbligatori.</footer>
  		</blockquote>
				
        <form class="form-horizontal" action="AggiungiIndirizzoSpedizione.jsp" name="myform" method="POST">
			<fieldset>

			<!-- Form Name -->
			<legend></legend>

				<!-- Select Basic -->
				<div class="form-group">
				    <div class="row" style="
				">
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
				         <input type="text" id="nominativo" name="nominativo" class="form-control" placeholder="Nome e Cognome" required="" autofocus="">
				        </div>
				         
				</div>
				    
				    <div class="row" style="margin-top: 1.5em;">
				  <div class="col-sm-6">
				         <label class="control-label" for="presso" style="text-align: left;float: left;">Presso C/O</label>
				        </div>
				         <div class="col-sm-6">
				         
				        </div>
				</div>
				       <div class="row" style="
				    margin-top: 0.5em;
				">
				  <div class="col-sm-12">
				         <input type="text" id="presso" name="presso" class="form-control" placeholder="Presso" autofocus="">
				        </div>
				         
				</div>
				    
				     <!--PASSWORD-->
				<div class="row" style="margin-top: 1.5em;">
				  <div class="col-sm-6">
				         <label class="control-label" for="via" style="text-align: left;float: left;">Via completa</label>
				        </div>
				         
				</div>
				       
				    
				    <div class="row" style="margin-top: 0.5em;">
				  <div class="col-sm-12">
				         <input type="text" id="via" name="via" class="form-control" placeholder="Via e numero civico" required="">
				        </div>
				         
				</div>
				 
				 
				<div class="row" style="margin-top: 1.5em;">
				  <div class="col-sm-7">
				         <label class="control-label" for="città" style="text-align: left;float: left;">Città</label>
				        </div>
				
				<div class="col-sm-3">
				         <label class="control-label" for="cap" style="text-align: center;/* float: left; */">CAP</label>
				        </div>
				
				<div class="col-sm-2">
				         <label class="control-label" for="provincia" style="text-align: center;/* float: left; */">Provincia</label>
				        </div>
				         
				</div>
				    <div class="row" style="margin-top: 0.5em;">
				  <div class="col-sm-7">
				         <input type="text" id="città" name="città" class="form-control" placeholder="Città" required="">
				        </div>
				    <div class="col-sm-3">
				         <input type="text" id="cap" name="cap" class="form-control" placeholder="CAP" required="" maxlength="5" style="
				    text-align: center;
				">
				        </div>
				<div class="col-sm-2">
				         <input type="text" id="provincia" name="provincia" class="form-control" placeholder="--" required="" maxlength="2" style="
				    text-align: center;
				">
				        </div>
				         
				</div><!--FINE PASSWORD-->
				

				
				<div class="row" style="margin-top: 1.5em;">
				  <div class="col-sm-6">
				         <label class="control-label" for="recapitoTelefonico" style="text-align: left;float: left;">Recapito Telefonico</label>
				        </div>
				         
				</div>
				<div class="row" style="margin-top: 0.5em;">
				  <div class="col-sm-9">
				         <input type="text" id="recapitoTelefonico" name="recapitoTelefonico" class="form-control" placeholder="Telefono">
				        </div>
				         
				</div>
				</div>
				  
				
				<br>
				<br>
				<br>

				<!-- Button -->
				<div class="form-group">
				  
				    <button id="registration" name="creaIndirizzo" class="btn btn-lg btn-info btn-block center-block" style="
				    width: 70% !important;" type="submit">Crea Indirizzo</button>
				  
				</div>
				
				</fieldset>
				</form>

	<%
	
		}	
	%>


      </div>
    </div>
<div class="col-sm-1"></div>
  </div>
</div>

<footer class="container-fluid">
 <%@include file="footer.html"%>
</footer>
</body></html>