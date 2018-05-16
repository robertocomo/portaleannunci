<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



<jsp:useBean id="registrationBean" scope="request" class="bean.RegistrationBean" />
<jsp:setProperty name="registrationBean" property="*" />

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

    <title>Registrati</title>

    <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="./css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./css/jumbotron-narrow.css" rel="stylesheet">

   
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        .ds-btn li{ list-style:none; float:left; padding:10px; }
		.ds-btn li a span{padding-left:15px;padding-right:5px;width:100%;display:inline-block; text-align:left;}
		.ds-btn li a span small{width:100%; display:inline-block; text-align:left;}

			
			/* Popover Header */
			.popover-title {
			       font-size: 1.1em;
			    padding: 0.5em 4em;
			    font-weight: 100;
			    text-align:center;
			}
			
			/* Popover Body */
			.popover-content {
			   font-size: 0.8em;
			    font-style: italic;
			    text-align: center;
			    letter-spacing: -0.03em;
			}
			
			
				.myclass {
					    background-color: #FFF;
			    		border: 1px solid rgba(103, 148, 234, 0.71);
			    		color: #444;
						}
				
				
				.myshadow {box-shadow: 0px 0px 10px 1px #0387ff;}
				

    </style>
  </head>

  <body style="
    font-family: -apple-system,system-ui,BlinkMacSystemFont,&quot;Segoe UI&quot;,Roboto,&quot;Helvetica Neue&quot;,Arial,sans-serif;
">

    <div class="container">
     <jsp:include page="header.jsp"/>

      <div class="jumbotron">
        <h1>Benvenuto</h1>
        <p class="lead">
</p><form class="form-horizontal" action="Registration.jsp" name="myform" method="POST">
<fieldset>

<!-- Form Name -->
<legend></br></legend>

<!-- Select Basic -->
<div class="form-group">
    <div class="row" style="
">
  <div class="col-sm-6">
         <label class="control-label" for="nome" style="text-align: left;float: left;">Nome</label>
        </div>
         <div class="col-sm-6">
         <label class="control-label" for="cognome" style="float: left;">Cognome</label>
        </div>
</div>
       <div class="row" style="
    margin-top: 0.5em;
">
  <div class="col-sm-6">
         <input type="text" id="nome" name="nome" class="form-control" placeholder="Nome" required="" autofocus="" value="<jsp:getProperty name="registrationBean" property="nome" />">
        </div>
         <div class="col-sm-6">
          <input type="text" id="cognome" name="cognome" class="form-control" placeholder="Cognome" required="" autofocus="" value="<jsp:getProperty name="registrationBean" property="cognome" />">
        </div>
</div>
    
    <div class="row" style="margin-top: 1.5em;">
  <div class="col-sm-6">
         <label class="control-label" for="email" style="text-align: left;float: left;">Email</label>
        </div>
         <div class="col-sm-6">
         <label class="control-label" for="confermaEmail" style="float: left;">Ripeti Email</label>
        </div>
</div>
       <div class="row" style="
    margin-top: 0.5em;
">
  <div class="col-sm-6">
         <input type="email" id="email" name="email" class="form-control" placeholder="Indirizzo Email" required="" autofocus="" value="<jsp:getProperty name="registrationBean" property="email" />">
        </div>
         <div class="col-sm-6">
          <input type="email" id="confermaEmail" name="confermaEmail" class="form-control" placeholder="Ripeti Email" required="" autofocus="">
        </div>
</div>
    
     <!--PASSWORD-->
<div class="row" style="margin-top: 1.5em;">
  <div class="col-sm-6">
         <label class="control-label" for="password" style="text-align: left;float: left;">Password</label>
        </div>
         
</div>
       
    
    <div class="row" style="margin-top: 0.5em;">
  <div class="col-sm-9">
         <input type="password" id="password" name="password" class="form-control" placeholder="Password" required="">
        </div>
         
</div>
 
 
<div class="row" style="margin-top: 0.5em;">
  <div class="col-sm-8">
         <label class="control-label" for="confermaPassword" style="text-align: left;float: left;">Ripeti Password</label>
        </div>
         
</div>
    <div class="row" style="margin-top: 0.5em;">
  <div class="col-sm-9">
         <input type="password" id="confermaPassword" name="confermaPassword" class="form-control" placeholder="Ripeti Password" required="">
        </div>
         
</div><!--FINE PASSWORD-->




<div class="row" style="margin-top: 1.5em;">
  <div class="col-sm-6">
          <label class="control-label" for="tipoUtente" style="text-align: left;float: left;">Tipologia Account</label>
        </div>
         
</div>

<div class="row" style="
    margin-top: 0.5em;
">
  
<div class="col-sm-4" style="
    /* padding-right: 2em; */
    padding-left: 3em;
">


       <button id="consumatore" name="consumatore" type="button" onclick="myClose()" class="btn btn-primary btn-lg myclass" style="display: block;width: 100%;padding: 0.4em 1.2em;font-size: 1.1em;font-weight: 400;">
    	<span class="glyphicon glyphicon-shopping-cart" style="
    padding-right: 0.2em;
"></span> Consumatore</button>
        </div>
         <div class="col-sm-4" style="
    /* margin-right: 5em; */
    /* padding-left: 2em; */
    padding-right: 3em;
    margin: 0;
">
          <button id="produttore" name="produttore" type="button" onclick="myOpen()" class="btn btn-primary btn-lg myclass" data-toggle="popover" style="display: block;width: 100%;padding: 0.4em 1.2em;font-size: 1.1em;font-weight: 400;margin: 0;" data-original-title="" title="">
    	<span class="glyphicon glyphicon-send" style="
    padding-right: 0.2em;
    padding-left: 0;
    margin-left: 0;
    float: left;
"></span> Produttore</button>
        </div>
</div>


<div id="demo" class="collapse">
<div class="row" style="margin-top: 1.5em;">
  <div class="col-sm-6">
         <label class="control-label" for="codiceFiscale" style="text-align: left;float: left;">Codice Fiscale</label>
        </div>
         
</div>
<div class="row" style="margin-top: 0.5em;">
  <div class="col-sm-9">
         <input type="text" id="codiceFiscale" name="codiceFiscale" class="form-control" placeholder="Codice Fiscale">
        </div>
         
</div>
</div>
<input type="hidden" name="tipoUtente" id="tipoUtente">
</div>
  

</br>
</br>
</br>
<%
          if (request.getParameter("registration") != null) {
        	  
        	  boolean allRight = true;      	  
        	         	  
        	  if(!registrationBean.checkEmail())
        	  {
        		  allRight = false;
        		 
        		%>
        	   	<div class="alert alert-danger" role="alert">
        	          <strong>Attenzione.</strong> I due indirizzi email inseriti non corrispondono.
        	        </div>
        	        <%
        	        
        	  }
        	  
        	  
        	  if(!registrationBean.checkPassword())
        	  {
        		  allRight = false;
        		 
        		%>
        	   	<div class="alert alert-danger" role="alert">
        	          <strong>Attenzione.</strong> Le due password inserite non corrispondono.
        	        </div>
        	        <%
        	        
        	  }
        	  
        	  
        	 if(!registrationBean.validate())
	        	  {
        			allRight = false;
        		 	
	        		%>
	        	   	<div class="alert alert-danger" role="alert">
	        	          <strong>Attenzione.</strong> Uno o più campi è vuoto o contiene un valore non valido.
	        	        </div>
	        	        <%
	        	  }
	        	  
	        	if(allRight)
	        	{
	        		  
	        	  
	        	  		int result = registrationBean.registerUser();
	        	  		
	        	  		if(result == -2)
		              	{
	        	  			
		              		%>
		              	   	<div class="alert alert-danger" role="alert">
		              	          <strong>Errore.</strong> Esiste già un utente registrato con questo indirizzo email.
		              	        </div>
		              	        <%
		              	}
	        	  		
	        	  		if(result == -1)
		              	{
		              		%>
		              	   	<div class="alert alert-danger" role="alert">
		              	          <strong>Errore</strong> generico nella registrazione. Riprovare
		              	        </div>
		              	        <%
		              	}
	        	  		
	        	  		
	        	  		if(result == 0)
		              	{
		              		%>
		              		
		              		<jsp:forward page="Login.jsp"> 
								<jsp:param name="registrationDone" value="success" /> 
							</jsp:forward> 	
								
		              	   	<div class="alert alert-success" role="alert">
							  <strong>Operazione eseguita !</strong>
							  <a href="Login.jsp" class="alert-link">Clicca qui per accedere alla pagina di login.</a>
							  
							</div>
		              	        <%
		              	}
	        	  		
	        	  }
        	  
        	  } 
        		  
        		  
        		
          
        %>





<!-- Button -->
<div class="form-group">
  
    <button id="registration" name="registration" class="btn btn-lg btn-info btn-block center-block" style="
    width: 70% !important;"">Registrati Ora</button>
  
</div>

</fieldset>
</form>


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
  
  
  <script>
  
  
  function myOpen(){
	  
	  $('.collapse').collapse('show');
	  $("#produttore").removeClass("myclass");
	  $("#produttore").addClass("myshadow");
	  $("#consumatore").addClass("myclass");
	  $("#consumatore").removeClass("myshadow");
	  $("#codiceFiscale").prop('required',true);
	  $("#tipoUtente").val("PRODUTTORE");
	  
	  
	  
	  
  }
  
 function myClose(){
	  
	  $('.collapse').collapse('hide');
	  $("#consumatore").removeClass("myclass");
	  $("#consumatore").addClass("myshadow");
	  $("#produttore").addClass("myclass");
	  $("#produttore").removeClass("myshadow");
	  $("#codiceFiscale").val("");
	  $("#codiceFiscale").prop('required',false);
	  $("#tipoUtente").val("CONSUMATORE");
	  
	  
  }
 
 $(document).ready(function(){
	    
	    $('#produttore').popover({title: "Attenzione", content: "In qualità di produttore ti chiediamo di inserire il tuo Codice Fiscale o Partita IVA.", trigger: "focus"}); 
	});
  
  
  </script>

</body></html>