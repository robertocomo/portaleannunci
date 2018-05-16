<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ page import="model.TipologiaUtente"%>
<%@ page import="bean.AnnuncioBean"%>  
<jsp:useBean id="gestioneAnnuncioBean" scope="page" class="bean.GestioneAnnuncioBean" />
<!-- <jsp:useBean id="annuncioBean2" scope="session" class="bean.AnnuncioBean" />-->
<jsp:useBean id="loginBean" scope="session" class="bean.LoginBean" />

<%	if(loginBean.userIsNotLogged() || !(loginBean.getTipo().equals(TipologiaUtente.PRODUTTORE)) || (request.getParameter("idAnnuncio") == null))	
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
			AnnuncioBean annuncioBean = null;

			if (request.getParameter("eliminaAnnuncio") == null)  
			{
				annuncioBean = gestioneAnnuncioBean.getSingoloAnnuncioByIdAnnuncio(idAnnuncio, loginBean.getId());
			
				//if((annuncioBean == null) || !(gestioneAnnuncioBean.isItemFound()))
				if(!(gestioneAnnuncioBean.isItemFound()))
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

    <title>I tuoi Annunci</title>

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
      
      <%
      
      	if (request.getParameter("eliminaAnnuncio") == null) 
      	{
      
      
      %>
      
				<div class="row" style="margin-bottom: 2em;">


      						<!-- PRIMO ELEMENTO -->
							  <div class="col-sm-12">
							  
							  <%
							  		if(!(gestioneAnnuncioBean.isUserIsAuthorized()))
							  		{
							  			%>	
							  				
							  				<div class="alert alert-danger" role="alert">
							  	        	  <strong>Errore:</strong> Non sei autorizzato ad effettuare questa operazione.
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

	<%
							  		if(annuncioBean.isAnnuncioPrivato())
							  		{
							  		%>	
									  			<div class="row" style="padding: 0 1em;">
									  	        <div class="col-sm-12" style="
									  	    text-align: center;
									  	    letter-spacing: 6px;
									  	    font-size: 0.7em;
									  	    border: 1px solid #DDD;
									  	    background-color: #d9534f;
									  	    color: #FFF;
									  	    border-radius: 0.8rem;
									  	    padding: 0.5em 0;
									  	    font-weight: 400;
									  	    margin-bottom: 0em;
									  	">Privato</div>
									  	    </div>
							  			
							  			
							  		<%	
							  		}
							  
							  
							  %>
							  
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
         <input type="text" class="form-control" id="prezzo" name="prezzo" readonly value="Prezzo :" style="background-color: white;
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
         <input type="text" class="form-control" id="quantità" name="quantità" readonly="" value="Quantità :" style="background-color: white;
    font-weight: 700;
">
        </div><div class="col-sm-2">
         <input type="text" class="form-control" id="quantità" name="quantità" readonly="" value="<%out.print(annuncioBean.getQuantità()); %>" style="background-color: white;
    text-align: center;
">
        </div>
         
</div>

<div class="row" style="
    margin-top: 5em;
    /* width: 80%; */
    display: flex;
    justify-content: center;
">
  

<div class="col-sm-3">
 <!-- <a href="ModificaAnnuncio.jsp?idAnnuncio=<%out.print(annuncioBean.getId());%>" class="btn btn-lg btn-warning btn-block center-block" >Modifica</a> -->
	<form class="form-horizontal" action="ModificaAnnuncio.jsp" name="myform" method="POST">
	      				 <input type="hidden" value="<%out.print(annuncioBean.getId());%>" name="idAnnuncio" id="idAnnuncio">
	      				 <input type="hidden" id="versioneAnnuncio" name="versioneAnnuncio" value="<%out.print(annuncioBean.getUltimaModifica()); %>">
	      					<button type="submit" id="modifica" name="modifica" class="btn btn-lg btn-warning btn-block center-block" >Modifica</button></form>
         
        </div>

<div class="col-sm-1"></div>
         
       <div class="col-sm-3">
         <button id="elimina" name="elimina" class="btn btn-lg btn-danger btn-block center-block" data-toggle="modal" data-target="#modalConferma">Elimina</button>

<div class="modal fade" id="modalConferma" tabindex="-1" role="dialog" aria-labelledby="modalConferma" aria-hidden="true" style="display: none;">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModal3Label" style="
    font-size: 1.75rem;
">Confermi di voler eliminare questo annuncio ?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
      </div>
      
      <div class="modal-footer">
      <form class="form-horizontal" action="GestioneAnnuncio.jsp?idAnnuncio=<%out.print(annuncioBean.getId());%>" name="myform" method="POST">
        <button type="reset" class="btn btn-secondary" data-dismiss="modal">No</button>
        <input type="hidden" id="versioneAnnuncio" name="versioneAnnuncio" value="<%out.print(annuncioBean.getUltimaModifica()); %>">
        <button type="submit" class="btn btn-danger" id="eliminaAnnuncio" name="eliminaAnnuncio">Conferma</button>
        </form>
      </div>
    </div>
  </div>
</div>
        </div>
         
</div>
    
    
  </div>
</div>

<%
							  			}

%>
							  </div>

      			

      						<!-- PRIMO ELEMENTO -->
							  

      			

      						<!-- PRIMO ELEMENTO -->
							  

      			</div>
      
      


      </div>
      
      <%
      
      }
      
      	else
      	{
      		int versioneAnnuncio = gestioneAnnuncioBean.parseRequest((request.getParameter("versioneAnnuncio")));
      		
      		if(gestioneAnnuncioBean.eliminaAnnuncio(idAnnuncio, loginBean.getId(),versioneAnnuncio ))
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
		  	        	  <strong>Errore.</strong> Il tuo annuncio potrebbe essere stato comprato ovvero già eliminato. 
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
      
      
      
      %>

      

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
  

</body></html>

<% }
		
		} %>
