# Portale Annunci



## Introduzione

Questo documento di visione ha lo scopo di presentare, analizzare e definire il
progetto Portale Annunci.

Nel seguente documento è dettagliato lo scopo del progetto, i suoi obiettivi
nonché posizionamento, gli stakeholder e tipologie di utilizzatori finali
coinvolti ed interessati, associate alle esigenze primarie che questi hanno
espresso in fase di definizione del progetto e alle relative funzionalità
identificate e concordate facenti parte del sistema software commissionato e
sviluppato.

2.  **Posizionamento**

    1.  **Scopo del Progetto**

> Il committente chiede di sviluppare una piattaforma che consenta agli
> utenti ad essa registrati di creare dei propri annunci di vendita
> ovvero finalizzare l'acquisto degli annunci presenti nel sistema.
>
> Queste funzionalità descritte costituiranno le due principali modalità
> di utilizzo della piattaforma, le quali, inoltre, dovranno essere
> adeguatamente differenziate e disaccoppiate: la sottomissione di
> annunci di vendita dovrà essere consentita ed abilitata solo ad una
> specifica categoria di utenti e, dunque, l'acquisto degli stessi
> annunci sarà riservato ad una distinta categoria di utenti.
>
> La piattaforma dovrà prevedere fine una funzionalità di ricerca degli
> annunci inseriti nel sistema, unendo dunque l'offerta di vendita con
> la domanda di acquisto.
>
> Il committente specifica che l'interazione e comunicazione fra gli
> attori interessati e le funzionalità implementate dalla piattaforma
> commissionata dovrà avvenire a mezzo di Web Application.

2.  **Opportunità di Business**

> Il contesto di utilizzo della piattaforma commissionata può essere
> classificato nella macro area di attività e-commerce, nella quale
> tuttavia non si ha un ruolo attivo nella vendita bensì si svolge un
> ruolo terzo di unificazione della domanda ed offerta da parte degli
> utilizzatori finali.
>
> Nell'attuale panorama economico di settore, nazionale ed
> internazionale, risultano essere già presenti, attive ed affermate
> diverse piattaforme alternative competitor che erogano un possibile
> analogo servizio. Si potrebbe dedurre quindi di conseguenza che le
> opportunità di sviluppo e crescita del progetto Portale Annunci
> possano essere fortemente influenzate da un contesto di mercato
> competitivo, e dunque difficilmente proficuo.
>
> Relazionando tuttavia tali informazioni alle statistiche e trend di
> crescita del settore e-commerce Business-to-Consumer e
> Consumer-to-Consumer con una vista sugli ultimi 5 anni si viene a
> delimitare un quadro risultante completamente diverso: la frequenza e
> maggior propensione degli utenti a cimentarsi in attività di shopping
> online, che sia in qualità di acquirente o di venditore, supera di ben
> oltre il tasso di affermazione di nuovi attori e realtà in tale
> settore.
>
> Il costante e sostanziale aumento del fatturato del settore economico
> dell'e-commerce conferma tale prospettiva, evidenziando un mercato con
> una domanda ed una potenzialità sempre più crescente e dunque aperta a
> nuovi competitor.
>
> Analizzando nello specifico sussistono varie strategie di Business
> affinché sia possibile generale degli utili dallo sviluppo ed
> affermazione di una piattaforma di intermediazione fra domanda ed
> offerta di annunci di vendita, le quali possono essere classificate in
> tre macro categorie:

-   Prevedere che la piattaforma o parte di essa o delle funzionalità
    siano accessibili previo pagamento, che a sua volta può essere di
    tipologia una tantum per utente o tramite sottoscrizione di una
    quota rinnovabile nel tempo.

-   Prevedere commissioni direttamente applicate, in via unitaria o
    percentuale, sulla compravendita degli Annunci presenti nella
    piattaforma e addebitate ad una tipologia di Utente.

-   Inserire nella piattaforma un sistema di banner pubblicitari,
    statici ovvero relazionati all'accesso o utilizzo di specifiche
    funzionalità, aderendo ad un circuito di affiliazione di società
    terze in grado di generare ricavi a seguito di visualizzazione o
    apertura del banner pubblicitario.

-   Ricavi indiretti, ed eventualmente postumi, scaturiti da un indotto
    di collaborazioni e sponsorizzazioni derivanti dal patrimonio di
    marca ovverosia dall'affermazione del valore dell'asset immateriale
    del brand Portale Annunci.

3.  **Glossario**

    3.  **Utente o Visitatore**

Un Visitatore, od altresì definito Utente, rappresenta un utilizzatore
del sistema o della piattaforma senza che questo ne faccia parte e che
dunque sia correttamente autenticato sulla stessa mediante proprie
credenziali di registrazione.

4.  **Registrazione**

Procedura con la quale un Visitatore si registra alla piattaforma
scegliendo un ruolo, diventandone dunque parte integrante.

5.  **Utente Registrato**

Rappresenta una persona che ha eseguito con successo la Registrazione al
sistema inserendo le informazioni richieste.

Possiede delle proprie credenziali di accesso alla piattaforma e dunque
ne è parte integrante.

6.  **Produttore**

Rappresenta una tipologia di Utente Registrato il quale ha accesso
esclusivo alla funzionalità di sottomissione di Annunci di vendita
personali, nonché gestione degli Ordini relativi ad Annunci
precedentemente inseriti.

7.  **Consumatore**

Rappresenta una tipologia di Utente Registrato il quale ha accesso
esclusivo alla funzionalità Acquisto di un Annuncio presente nel sistema
e finalizzazione di un Ordine, potendo dunque accedere alle informazioni
associate a quest'ultimo.

8.  **Annuncio**

Rappresenta un'astrazione del bene messo in vendita all'interno della
piattaforma da un utente Produttore. È principalmente caratterizzato da
un nome, una descrizione del bene venduto, un valore rappresentante la
quantità disponibile, il prezzo di vendita, una Categoria prevista e
modellata dalla piattaforma, uno Stato modellato dalla piattaforma ed
una foto descrittiva.

9.  **Stato Annuncio**

Rappresenta una etichetta di stato associata ad un Annuncio presente nel
sistema, che a seconda del suo valore modellato discrimina e filtra le
funzionalità associate o relative a quell'Annuncio.

10. **Acquisto**

Procedura con la quale un utente di tipo Consumatore può finalizzare
l'acquisto di un Annuncio presente nel sistema, sottomettendo un nuovo
Ordine.

11. **Ordine**

Rappresenta un'astrazione derivante da un Acquisto effettuato da parte
di un Consumatore, modellando le informazioni riguardanti l'Annuncio
acquistato, la quantità acquistata, il prezzo di acquisto ed importo
complessivo, la data di sottomissione e l'Indirizzo di Spedizione
associato.

12. **Indirizzo di Spedizione**

Rappresenta e modella le informazioni inserite da un utente di tipo
Consumatore sull'effettivo concreto nominativo ed indirizzo geografico
al quale un Ordine dovrà essere spedito da parte dell'utente Produttore.

13. **Ricerca**

Procedura con la quale deve essere possibile poter effettuare una
ricerca fra tutti gli Annunci presenti nel sistema, indicando
obbligatoriamente una chiave testuale di ricerca, la quale dovrà
correlare ed individuare eventuali parole corrispondenti contenute nel
titolo di un Annuncio o nella sua descrizione, ed opzionalmente una
Categoria di filtraggio fra quelle modellate dal sistema.

14. **Ricerca Avanzata**

Procedura con la quale deve essere possibile poter effettuare una
ricerca fra tutti gli Annunci presenti nel sistema, indicando
obbligatoriamente una chiave testuale di ricerca, la quale dovrà
correlare ed individuare eventuali parole corrispondenti contenute nel
titolo di un Annuncio o nella sua descrizione, ed come parametri
opzionali di filtraggio una Categoria fra quelle modellate dal sistema
ed un range di prezzo minimo e massimo.

4.  **Stakeholder Requests & Needs**

    15. Meccanismo di registrazione al sistema

    16. Un Utente Registrato deve aver inserito il proprio nome,
        cognome, email personale e una password

    17. Un Produttore per potersi registrare nel sistema deve aver
        inserito il proprio codice fiscale o partita iva

    18. Meccanismo di autenticazione al sistema tramite delle
        credenziali di accesso personali

    19. Un Utente Registrato deve potersi autenticare mediante il
        proprio indirizzo email e la password scelti in fase di
        registrazione

    20. Si deve poter creare ed inserire un nuovo Annuncio nel sistema

    21. Solo un utente Produttore può inserire nuovo Annuncio nel
        sistema

    22. Un utente Produttore deve poter modificare un proprio Annuncio

    23. Un utente Produttore deve poter eliminare un proprio Annuncio

    24. Un Annuncio deve essere individuabile in modo univoco

    25. Un Annuncio deve avere uno Stato associato previsto dal Sistema

    26. Un Annuncio è in stato privato deve essere visualizzabile
        esclusivamente dall'utente Produttore che lo ha creato

    27. Un Annuncio deve avere una Categoria associata modellata dal
        sistema

    28. Le possibili categorie dovranno essere "Abbigliamento",
        "Arredamento", "Creatività", "Elettronica", "Libri", "Musica"

    29. Si deve poter ricercare gli Annunci presenti nel sistema
        inserendo una chiave di ricerca per il titolo o per la
        descrizione

    30. La funzionalità di ricerca deve essere accessibile anche ai
        Visitatori

    31. Deve essere implementata una funzionalità di ricerca avanzata
        per gli utenti Consumatori limitando i risultati per fascia di
        prezzo

    32. Eseguendo una ricerca avanzata deve essere possibile ordinare i
        risultati

    33. La possibilità di ispezionare e guardare i dettagli di un
        Annuncio deve essere limitata agli utenti di tipo Consumatore

    34. Un utente Consumatore deve poter inserire nel proprio account
        uno o più Indirizzi di Spedizione che saranno usati in fase di
        Acquisto

    35. L'acquisto di un Annuncio, creando un Ordine, deve essere
        limitata esclusivamente agli utenti Consumatori

    36. Se un utente Consumatore non ha inserito alcun Indirizzo di
        Spedizione non può effettuare un Acquisto

    37. Un utente Consumatore deve poter visualizzare l'elenco dei
        propri Acquisti effettuati e dunque Ordini associati

    38. Un utente Produttore deve poter visualizzare l'elenco dei propri
        Ordini ricevuti relativi ad Acquisti di propri Annunci

    39. Le informazioni dell'Annuncio associato ad un Ordine devono
        essere conservate in modo definitivo e consistente

    40. Un utente Consumatore può effettuare Acquisti di Annunci
        effettivamente presenti nel sistema

    41. Il sistema deve essere sviluppato e scritto in linguaggio Java

    42. L'interazione con il sistema da parte di tutti gli utilizzatori
        finali dovrà essere tramite funzionalità accessibile come web
        applicazion

    43. La web application deve essere sviluppata in modo responsive e
        dunque essere usufruibile da qualsiasi dispositivo di accesso
        web

5.  **Features**

    44. Il sistema deve consentire la registrazione allo stesso da parte
        di tipologie di utenti Consumatore e Produttore

    45. Il sistema deve consentire l\'autenticazione allo stesso da
        parte di tipologie di utenti Consumatore e Produttore

    46. Il sistema deve consentire ad una tipologia di utente Produttore
        di creare, modificare ed eliminare i propri Annunci.

    47. Il sistema deve consentire di impostare un Annuncio nello stato
        di Privato, tale che non sia né acquistabile né consultabile

    48. Il sistema deve consentire l\'ispezione di un Annuncio pubblico
        esclusivamente alla tipologia di utenti Consumatore

    49. Il sistema deve consentire l\'acquisto completo o parziale degli
        Annunci esclusivamente da parte di Consumatori che abbiano
        associato almeno un Indirizzo di Spedizione

    50. Il sistema deve permettere ad un qualsiasi utente di effettuare
        una ricerca fra gli Annunci specificando una parola chiave ed
        una categoria opzionale

    51. Il sistema deve permettere ad un Utente Registrato nel sistema
        di effettuare una ricerca avanzata degli Annunci, specificando
        un prezzo minimo e massimo

    52. Il sistema deve permettere ad una tipologia di utente
        Consumatore di inserire o modificare un Indirizzo di Spedizione
        associato, creando una propria rubrica di Indirizzi di
        Spedizione

    53. Il sistema deve permettere ad una tipologia di utente Produttore
        di modificare un proprio Annuncio che abbia già un ordine
        associato

    54. Il sistema deve prevedere che un utente Produttore abbia
        inserito il proprio codice fiscale o partiva iva in fase di
        registrazione

    55. Il sistema deve permettere ad una tipologia di utente
        Consumatore di visualizzare tutti i propri Ordini effettuati

    56. Il sistema deve prevedere meccanismi di consistenza in caso un
        Annuncio venga modificato durante una fase di acquisto da parte
        di un utente Consumatore

    57. Il sistema deve permettere ad una tipologia di utente Produttore
        di visualizzare tutti i propri Ordini ricevuti

    58. Il sistema deve permettere ad una tipologia di utente Produttore
        di modificare lo stato di un Ordine riguardo la spedizione

    59. Il sistema deve consentire ad un Consumatore di accedere alle
        informazioni dell\'Annuncio associato ad un Ordine effettuato
