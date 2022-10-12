# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Siri Esteri Berg-Johnsen, s364735, s364735@oslomet.no
* Andreas Dårstad, s364765, s364765@oslomet.no
* Ylva Evenrud, s364748, s364748@oslomet.no
* Miriam Sarpong, s364766, s364766@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Siri har hatt hovedansvar for oppgave 1, 4, 7, 9
* Andreas har hatt hovedansvar for oppgave 6
* Ylva har hatt hovedansvar for oppgave 5 
* Miriam har hatt hovedansvar for oppgave 8
* Vi har i felleskap løst oppgave 2, 3 og 10
* 3a var i hovedsak løst av Siri og Andreas
* 3b var i hovedsak løst av Ylva og Miriam

# Oppgavebeskrivelse

I oppgave 1 så gikk vi frem ved å først sjekke om selve tabbelen a er null, og da kaste en Exception. Etter dette sjekker vi om a[i] er null, siden en slik verdi ikke skal bli en node. Bare den første verdien vil bli hode, siden hode fremdeles er null. Alle nodene vil være hale på et tidspunkt, men den siste vil få nabo lik null, da denne initieringen skjer utenfor for-løkken. 

I oppgave 2 vi startet med å sjekke om listen er tom, og returnerer "[]" om den er det. Ellers ved hjelp av StingBuilder lager vi en tekststreng som starter med å legge til hode verdien til listen. Etter dette looper vi igjennom listen fra hode til halen ved hjelp av neste pekeren til "current"/noden vi er på. Etter halen blir lagt til, blir listen lukket, og tekststrengen som er bygget blir returnert. 
På omvendtString gikk vi fram på samme måte, men vi looper og legger til fra halen, og mot hodet. Returnerer på samme måte

I oppgave 3a så gikk vi frem ved å sjekke om indeks er null, om det stemmer så returnerer vi hode.
Så sjekker vi om indeksen ligger i venstre halvdel av listen. Hvis det stemmer setter vi current til hode og deretter looper gjennom listen ved hjelp av en for løkke.
Current oppdateres til neste verdi for hver gang den looper og for løkka stopper når i er like stor som indeks. Deretter blir current returnert som da har verdien som ligger på den gitte indeks plassen.
Dersom indeksen ligger i høyre halvdel går vi inn i else delen og gjør det samme som i tilfellet der indeks er i første halvdel, men for løkka starter på hale istedenfor hode.

I oppgave 3b så gikk vi frem ved å lage en sjekk om intervallet [fra:til> er lovlig eller gyldig. Deretter gikk jeg ut ifra å initialisere hva som er current verdien, som skal da starte fra, fra-verdien. Slik som oppgaven ber om, skulle vi retunere en liste, instans av klassen DobbeltLenketListe. 

I oppgave 4 så gikk vi frem ved å iterere gjennom listen fra hode-noden. Koden sjekker for hver current-node om den har verdi lik ønsket verdi, og hvis ikke itererer den til neste node ved hjelp av neste-pekeren. Hvis verdien er lik, returneres i med en gang. Hvis verdien ikke stemmer med noen av nodenes verdier, returneres -1.
For inneholder() kaller den på indeksTil() og returnerer false hvis den får tilbake -1 og ellers returnerer den true

I oppgave 5 så gikk det frem ved å først sjekke om verdien og indeksen man får inn er gyldig. Er de gyldige blir det laget en node med riktig verdi og indeks. Etter dette sjekkes indeks mot listen, og sjekker om listen har noen verdier i seg for å sjekke om den er tom. Er den tom setter den hode og hale til denne nye verdien og neste/forrje pekere mot null. 
Så sjekkes det om indeksen skal settes som hale, hode eller i midten av to tall. Dette gjøres i if, else if og else etter hvor den ska plasseres. Det blir også satt neste og forrje pekere etter riktig ifth hvor den nye plasseres. 

I oppgave 6 så har jeg skrevet en felles beskrivelse for begge fjern metodene siden de er bygd opp relativt likt, slik at det ikke skal bli en alt for lang beskrivelse. 
Vi gikk frem ved å dele opp i 3 forskjellige sjekker i begge fjern metodene, en for tilfellet der verdien som skal fjernes er på indeks 0(hode), siste index(hale), eller i mellom disse to.
I fjern(int indeks) metoden starter vi med å kalle på indekskontroll mens i fjern(T verdi) sjekker vi om verdi==null, begge disse tester arrayets gyldighet. 
Deretter lagde vi to hjelpevariabler, posisjon for å holde styr på indeksen og fjernes som skulle lagre verdien som ble fjernet og returnere den. 
Fjernet brukes kun i fjern(int indeks) ettersom at i fjern(T verdi) skal det kun reture true eller false. 
Index til verdien blir sjekket om den er først, sist eller i mellom og går inn i if setningen som passer i det gitte tilfellet.
Dersom den er først eller sist vil hode/hale pekere bli satt til null eller verdien før/etter verdien som skal fjernes, deretter oppdateres antall og endringer og evt returnerer true.
Dersom verdien/indeksen er i mellom vil den gå inn i en for løkke som looper gjennom lista til den finner verdien og fremover/bakover pekere vil peke på verdien før/etter verdien vi fjerner, slik at den blir slettet.
Deretter vil antall/endringer bli oppdatert slik som i de andre if/else setningene.

I oppgave 7 så gikk vi frem for metode 1, ved å bruke to variabler, en for current og en for next, så en fremdeles 
kunne iterere gjennom lista, selvom man satte alle verdier i current til null. Satte så antall=0, hode=null og
hale=null. Plusset også på endringer
Dette brukte maskinen 20-25ms på i følge terminalen.
For metode 2, brukte vi en for løkke som itererer fra 0 til, men ikke med antall, som kaller på fjern(0).Satte så antall=0, hode=null og
hale=null. Plusset også på endringer
Dette brukte maskinen 20-25ms på i følge terminalen.
+Siden metode 2 gjenbruker mye kode, ønsket vi å velge denne. Det er også fint at det er mindre ny kode som må skrives.

I oppgave 8 så gikk vi frem ved å kode alle de metodene i oppgaven. I metoden T next() der vi sjekket om 
iteratorendringer er lik endringer. Hvis det ikke er det kaster vi en feilmelding og hvis det er ikke flere igjen
i listen (metoden hasNext()) kaster vi også en feilmelding. I metoden Iterator<T> iterator(), 
returnerte vi en instans av iteratorklassen. Mens i konstruktør metoden
(private  DobbeltLenketListeIterator(int  indeks)) - >
bruker vi en for-loop for å iterere over listen for å da sette pekeren til den noden som hører til oppgitt indeks

I oppgave 9 så gikk vi frem ved å lage kode for alle de forskjellige tilfellene. Begynte med dette fordi jeg var usikker
på hvordan feilmeldingene skulle håndteres. Etter at tilfellene var kodet, la vi inn feil-håndteringene.


I oppgave 10 så gikk vi frem ved å først utifra programkode 1.3.8 c) (innsetningssortering) og programkode 1.1.8 d) (bytt()metoden) i kompendiet. 
Vi valgte å sammenlikne objektet med seg selv, fordi vi møtte på problemer når vi ikke gjorde det. Når liste.hent(i) var "mindre"/"dårligere" 
enn liste.hent(j), ved hjelp av c.compare(), skal disse bytte plass, som blir gjort ved hjelp av programkode 1.1.8 d fra kompendiet.
