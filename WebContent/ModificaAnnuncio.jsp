<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>

<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.*"%>


<%@ page import="model.TipologiaUtente"%> 
<%@ page import="bean.AnnuncioBean" %>
<%@ page import="model.CategoriaAnnuncio"%>  
<%@ page import="model.StatoAnnuncio" %>

<jsp:useBean id="gestioneAnnuncioBean" scope="page" class="bean.GestioneAnnuncioBean" />
<!-- <jsp:useBean id="annuncioBean2" scope="session" class="bean.AnnuncioBean" /> -->
<jsp:useBean id="loginBean" scope="session" class="bean.LoginBean" />
<jsp:useBean id="annuncioBean" scope="request" class="bean.AnnuncioBean" />
<jsp:setProperty name="annuncioBean" property="*" />

<%	if(loginBean.userIsNotLogged() || !(loginBean.getTipo().equals(TipologiaUtente.PRODUTTORE)) || 
		((request.getParameter("modifica") == null) && !(ServletFileUpload.isMultipartContent(request)) )  )	
	{ 
		response.sendRedirect("./Login.jsp");	
	}

	else
	{
		int idAnnuncio = gestioneAnnuncioBean.parseRequest((request.getParameter("idAnnuncio")));
		
		if(idAnnuncio < 0)
		{
		%>
			<jsp:forward page="index.jsp" />
		<%	
		}
		
		else
		{

			if (!ServletFileUpload.isMultipartContent(request)) 
			{
				int versioneAnnuncio = gestioneAnnuncioBean.parseRequest((request.getParameter("versioneAnnuncio")));
				annuncioBean = gestioneAnnuncioBean.getSingoloAnnuncioByIdAnnuncio(idAnnuncio, loginBean.getId(), versioneAnnuncio);
			
				if( ((annuncioBean == null) || !(gestioneAnnuncioBean.isItemFound())) && !(gestioneAnnuncioBean.isTransactionError()))
				{
				%>
					<jsp:forward page="index.jsp" />
				<%	
				}
			
		
			}
		
		
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

    <title>Modifica Annuncio</title>

    <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="./css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./css/jumbotron-narrow.css" rel="stylesheet">
    <link href="./css/bootstrap-toggle.min.css" rel="stylesheet">

   
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
    	
    	.filestyle .btn {padding: 0 1em 0 1em !important; height: 1.6em} 
    
    
        .ds-btn li{ list-style:none; float:left; padding:10px; }
        .ds-btn li a span{padding-left:15px;padding-right:5px;width:100%;display:inline-block; text-align:left;}
        .ds-btn li a span small{width:100%; display:inline-block; text-align:left;}

        .stateToggle .btn {
		        	padding: 6px 12px !important;
		    text-align: center;
		    /* vertical-align: middle; */
		    font-size: 1.05em;
		    line-height: 1.8em;
		    font-style: italic;
		    font-weight: 300;
		    letter-spacing: 2px;
		        
        }


    </style>
  </head>

<body style="
    font-family: -apple-system,system-ui,BlinkMacSystemFont,&quot;Segoe UI&quot;,Roboto,&quot;Helvetica Neue&quot;,Arial,sans-serif;
">

    <div class="container">
     <jsp:include page="header.jsp"/>

      <div class="jumbotron">
      
      <%
      
     // AnnuncioBean annuncioBean = (AnnuncioBean) request.getAttribute("annuncioBean");
      
      
      	
		
      
      	
		if (ServletFileUpload.isMultipartContent(request))
		{
			
			ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
			Hashtable multipartParams = new Hashtable();
			List<FileItem> multifiles = sf.parseRequest(request);
			
			FileItem picture = null;
			
			for(FileItem item : multifiles)
			{
			    if (item.isFormField()) 
			    	multipartParams.put(item.getFieldName(), item.getString());
			    else
			    		picture = item;
    
			}
			
			if(picture.getName().isEmpty())
				annuncioBean.setFoto("null");
			else
				annuncioBean.setFoto(picture.getName());
			
			annuncioBean.setTitolo((String) multipartParams.get("titolo"));
			annuncioBean.setCategoria((String) multipartParams.get("categoria"));
			annuncioBean.setQuantità((String)multipartParams.get("quantità"));
			annuncioBean.setPrezzo((String) multipartParams.get("prezzo"));
			annuncioBean.setDescrizione((String) multipartParams.get("descrizione"));
			annuncioBean.setStatoAnnuncio((String) multipartParams.get("statoAnnuncio"));
					
			
			
			
			if (annuncioBean.validate()) 
			{
				
				gestioneAnnuncioBean.setAnnuncioCorrente(annuncioBean);
				
				int versioneAnnuncio = gestioneAnnuncioBean.parseRequest((String) multipartParams.get("versioneAnnuncio"));
				
				
				if(gestioneAnnuncioBean.modificaAnnuncio(idAnnuncio, loginBean.getId(), versioneAnnuncio, picture))
				{
					
					
				%>	
						
						<div class="alert alert-success" role="alert">
							  <strong>Operazione eseguita !</strong>
							  <a href="AnnunciProduttore.jsp" class="alert-link">Clicca qui per vedere i tuoi annunci.</a>
							  
						</div>
						
				<%	
				}
				
				else
				{
					if(!gestioneAnnuncioBean.isUserIsAuthorized())
					{
			  			%>	
			  				
			  				<div class="alert alert-danger" role="alert">
			  	        	  <strong>Errore:</strong> Non sei autorizzato ad effettuare questa operazione.
			  	       		</div>
			  				
			  			<%	
			  		}
					
					if(!gestioneAnnuncioBean.isItemFound())
					{
			  			%>	
						
						<div class="alert alert-warning" role="alert">
			        	  <strong>Errore nel trovare l'annuncio</strong> 
			       		</div>
						
						<%	
			  		}
					
					
					if(gestioneAnnuncioBean.isTransactionError())
					{
			  			%>	
			  				
			  				<div class="alert alert-danger" role="alert">
			  	        	  <strong>Errore.</strong> Il tuo annuncio potrebbe essere stato comprato ovvero già modificato.
			  	        	    <br>
		  	        	  <%out.print("ID annuncio: \t" + idAnnuncio); %>
		  	        	   <br>
		  	        	  <%out.print("Versione \t" + versioneAnnuncio); %>
			  	       		</div>
			  				
			  			<%	
			  		}
					
					
					%>	
	  				
	  				<div class="alert alert-danger" role="alert">
	  	        	  <strong>Errore.</strong> La modifica non è stata apportata.
	  	       		</div>
	  				
	  				<%	
					
					
					
					
				}
				
			}
			
			else
			{
				%>	
					
					<div class="alert alert-warning" role="alert">
		        	   <strong>Attenzione.</strong> Uno o più campi è vuoto o contiene un valore non valido.
		       		</div>
					
				<%	
			}

		}
      
		else
		{
			
			if (gestioneAnnuncioBean.isTransactionError())
	      	{
	      		%>	
					
					<div class="alert alert-danger" role="alert">
		        	  <strong>Errore.</strong> Il tuo annuncio potrebbe essere stato comprato ovvero già modificato.
		        	       	 
		       		</div>
					
				<%	
	      		
	      	}
			else
			{
			
			
			%>	
				
				<blockquote style="border-left: 5px solid #1070ea;">
		    		<p>Modifica il tuo Annuncio</p>
		    		<footer>Ricorda che tutti i campi sono obbligatori.</footer>
  				</blockquote>
				
	
      
  	
  		
		<form class="form-horizontal" action="ModificaAnnuncio.jsp?idAnnuncio=<%out.print(annuncioBean.getId());%>" name="myform" method="POST" enctype="multipart/form-data">
		<fieldset>
		
		<!-- Form Name -->
		<legend></legend>
		
		<!-- Select Basic -->
		<div class="form-group">
		
		    <div class="row" style="">
		  <div class="col-sm-9"></div>
		 <div class="col-sm-3 stateToggle" >
		        <input type="checkbox" name="toggleStatoAnnuncio" id="toggleStatoAnnuncio" <%out.print(annuncioBean.isAnnuncioPrivato() ? "unchecked" : "checked"); %> data-toggle="toggle" data-on="Pubblico" data-off="Privato" data-onstyle="success" data-offstyle="danger"></div>
		        <input type="hidden" name="statoAnnuncio" id="statoAnnuncio" value="<%out.print(annuncioBean.getStatoAnnuncio());%>">
		        </div>
		
		    <div class="row" style="
		">
		  <div class="col-sm-6">
		         <label class="control-label" for="titolo" style="text-align: left;float: left;">Titolo</label>
		        </div>
		         <div class="col-sm-6">
		         
		        </div>
		</div>
		       <div class="row" style="
		    margin-top: 0.5em;
		">
		  <div class="col-sm-12">
		         <input type="text" id="titolo" name="titolo" class="form-control" placeholder="Titolo" required="" autofocus="" value="<%out.print(annuncioBean.getTitolo()); %>" >
		        </div>
		         
		</div>
		    
		    <div class="row" style="margin-top: 1.5em;">
		  <div class="col-sm-6">
		         <label class="control-label" for="descrizione" style="text-align: left;float: left;">Descrizione</label>
		        </div>
		         <div class="col-sm-6">
		         
		        </div>
		</div>
		       <div class="row" style="
		    margin-top: 0.5em;
		">
		  <div class="col-sm-12">
		         <textarea class="form-control textarea" rows="10" name="descrizione" id="descrizione" maxlength="750" placeholder="Descrizione" required="" ><%
		         out.print(annuncioBean.getDescrizione()); %></textarea>
		        </div>
		         
		</div>
		    
		     
		<div class="row" style="margin-top: 1.5em;">
		  <div class="col-sm-6">
		         <label for="categoria" style="text-align: left !important;float: left;">Categoria</label>
		        </div>
		         
		</div>
		       
		    
		    <div class="row" style="margin-top: 0.5em;">
		  <div class="col-sm-9">
		         <select class="form-control" id="categoria" name="categoria" required="">
		      
				    <%
			    	 	for (CategoriaAnnuncio type : CategoriaAnnuncio.values())
			    	 	{
			    		%>	 
			    			<option value="<%out.print(type.name()); %>" <%out.print( type.name().toUpperCase().equals(annuncioBean.getCategoria().toUpperCase()) ? "selected" : ""  );
			    			%>><%out.print(CategoriaAnnuncio.getCapitalizedName(type.name())); %></option> 
			    		<%
			    	 	}
		    	
		    	
		    		%>
   		
		  </select>
		        </div>
		        
		       
		         
		</div>
		 
		 
		<div class="row" style="margin-top: 1.5em;">
		  <div class="col-sm-6">
		         <label class="control-label" for="prezzo" style="text-align: left;float: left;">Prezzo</label>
		        </div>
		         <div class="col-sm-6">
		         
		        </div>
		</div>
		<div class="row" style="
		    margin-top: 0.5em;
		">
		  <div class="col-sm-4">
		         <input type="number" class="form-control" id="prezzo" name="prezzo" min="0" step="0.01" placeholder="Prezzo" required="" value="<%out.print(annuncioBean.getPrezzo()); %>">
		        </div>
		         
		</div><div class="row" style="margin-top: 0.8em;">
		  <div class="col-sm-6">
		         <label class="control-label" for="quantità" style="text-align: left;float: left;">Quantità</label>
		        </div>
		         <div class="col-sm-6">
		         
		        </div>
		</div>
		
		<div class="row" style="
		    margin-top: 0.5em;
		">
		  <div class="col-sm-4">
		         <input type="number" class="form-control" id="quantità" name="quantità" min="1" step="1" placeholder="Quantità" required="" value="<%out.print(annuncioBean.getQuantità()); %>">
		        </div>
		         
		</div>
		
		
		
		
		    <div class="row" style="margin-top: 2.5em;">
		  <div class="col-sm-6">
		         <label class="control-label" for="foto" style="text-align: left;float: left;">Foto</label>
		        </div>
		         <div class="col-sm-6">
		         
		        </div>
		</div>
		<div class="row" style="margin-top: 0.8em;">
		  
		         <div class="col-sm-12 filestyle">
		      <input type="file" class="filestyle" data-buttonText="Sfoglia" style="padding: 0 !important" name="foto" id="foto">
		        </div>
		</div>
		
		</div>
		  
		
		<br>
		<br>
		<br>
		
		
		
		
		
		
		<!-- Button -->
		<div class="form-group">
		  	<input type="hidden" id="versioneAnnuncio" name="versioneAnnuncio" value="<%out.print(annuncioBean.getUltimaModifica()); %>">
		    <button id="modificaAnnuncio" name="modificaAnnuncio" class="btn btn-lg btn-info btn-block center-block" style="
		    width: 70% !important;" type="submit">Modifica Annuncio</button>
		  
		</div>
		
		</fieldset>
		</form>

	<%

		}
			
		

	%>


      </div>

      

      <footer class="footer">
        <%@include file="footer.html"%>
      </footer>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./js/ie10-viewport-bug-workaround.js"></script>
     <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-filestyle.min.js"> </script>
    
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="./js/bootstrap.min.js"></script>
    
	<script src="./js/bootstrap-toggle.min.js"></script>
	<script>
  	$(function() {
    $('#toggleStatoAnnuncio').change(function() {
    	
    	
    	
      if($(this).prop('checked'))
    	  document.getElementById("statoAnnuncio").value = "<%out.print(StatoAnnuncio.PUBLIC.name());%>";
      else
    	  document.getElementById("statoAnnuncio").value = "<%out.print(StatoAnnuncio.PRIVATE.name());%>";
      
    })
  })
</script>
  

</body></html>

<%		}
	
	}
} %>
