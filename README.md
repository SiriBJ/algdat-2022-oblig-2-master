# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Siri Esteri Berg-Johnsen, s364735, s364735@oslomet.no
* Andreas Dårstad, s364765, s364765@oslomet.no
* Ylva Evenrud, s364748, s364748@oslomet.no
* Miriam Sarpong, s364766, s364766@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Siri har hatt hovedansvar for oppgave 1, 4, 7
* Andreas har hatt hovedansvar for oppgave 6
* Ylva har hatt hovedansvar for oppgave 5 
* Miriam har hatt hovedansvar for oppgave 5
* Vi har i felleskap løst oppgave 2 og 3
* 3a var i hovedsak løst av Siri og Andreas
* 3b var i hovedsak løst av Ylva og Miriam

# Oppgavebeskrivelse

I oppgave 1 så gikk vi frem ved å først sjekke om selve tabbelen a er null, og da kaste en Exception. Etter dette sjekker vi om a[i] er null, siden en slik verdi ikke skal bli en node. Bare den første verdien vil bli hode, siden hode fremdeles er null. Alle nodene vil være hale på et tidspunkt, men den siste vil få nabo lik null, da denne initieringen skjer utenfor for-løkken. 

I oppgave 2 så brukte vi en ... til å ...

I oppgave 3a så gikk vi frem ved å

I oppgave 3b så gikk vi frem ved å

I oppgave 4 så gikk vi frem ved å iterere gjennom listen fra hode-noden. Koden sjekker for hver current-node om den har verdi lik ønsket verdi, og hvis ikke itererer den til neste node ved hjelp av neste-pekeren. Hvis verdien er lik, returneres i med en gang. Hvis verdien ikke stemmer med noen av nodenes verdier, returneres -1.
For inneholder() kaller den på indeksTil() og returnerer false hvis den får tilbake -1 og ellers returnerer den true

I oppgave 5 så gikk vi frem ved å

I oppgave 6 så gikk vi frem ved å

I oppgave 7 så gikk vi frem for metode 1, ved å bruke to variabler, en for current og en for next, så en fremdeles 
kunne iterere gjennom lista, selvom man satte alle verdier i current til null. Satte så antall=0, hode=null og
hale=null. Plusset også på endringer
Dette brukte den 11ms,på en stasjonær pc, på i følge terminalen.
For metode 2, brukte vi en for løkke som itererer fra 0 til, men ikke med antall, som kaller på fjern(0).Satte så antall=0, hode=null og
hale=null. Plusset også på endringer
Dette brukte maskinen 11ms på, på en stasjonær pc, i følge terminalen.
Siden metode 2 gir mye kortere kode og gjenbruker kode, valgte vi å bruke denne.

I oppgave 8 så gikk vi frem ved å

I oppgave 9 så gikk vi frem ved å

I oppgave 10 så gikk vi frem ved å
