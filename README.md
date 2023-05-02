# Jasm

Jasm je orodje, ki omogoča vstavljanje javanske zložne kode na način, kot je to mogoče z `asm` stavki v jeziku C. <br>

Program sestavlja uporabniški vmesnik v obliki spletne aplikacije in zaledni del. Na uporaniškem vmesniku je možno pisati javansko kodo, jo prevesti in nato prenesti razredno ali izvorno datoteko. Prikazani so tudi rezultati izvajanja in nabor konstant. 

## Navodila za uporabo

Predpogoj za uporabo programa je delujoča namestitev [Docker](https://www.docker.com) in [docker-compose](https://docs.docker.com/compose/).

### Prenos in zagon

Za prenos programa in zagon lahko uporabimo ukaze (MacOS/Linux):

```bash
git clone https://github.com/aljazsustar/jasm
cd jasm
docker-compose up -d
```

Aplikacija je po zagonu dostopna preko brskalnika na naslovu [http://localhost:6969](http://localhost:6969).

### Uporaba

- Ne spreminjaj imena razreda `Main`. To ime se uporablja za nalaganje razreda po prevajanju izvorne kode.
- Ne izbriši uvoženih paketov v prvih dveh vrsticah kode. Potrebni sta za prevajanje anotacij.
- Zložno kodo piši v sledečem formatu:
```
/*
<bytecode>
*/
```
Simbola za zečetek in konec komentarja (`/*` in `*/`) morata biti pisana v svoji vrstici. Opisano strukturo imenujemo `jasm` blok.
- Metode, ki vsebujejo `jasm` bloke, morajo biti označene z anotacijami `@Jasm`. Anotacija sprejme seznam anotacij `@Block`, ki sprejmejo 2 parametra: `start` in `end`. Vrednost `start` mora biti vrstica, kjer se začne zložna koda v `jasm` bloku, vrednost `end` pa mora biti vrstica, kjer se zložna koda konča.
- Če želimo v metodi vračati vrednost, potem moramo v metodi vračati konstantno vrednost. V `jasm` blok nato zapišemo ukaze za vračanje. Primer metode, kjer bi želeli vrniti celo število v `jasm` bloku:

```java
public static int f() {
    /*
    iconst_5
    ireturn
    */
    return 0;
  }
```
Metoda `f` vrne vrednost `5`.
- Metode lahko kličemo na dva načina. Primera:
```
invokestatic <indeks>
```
ali
```
invokestatic #<ime_metode>
```
. V prvem primeru ukazu podamo indeks v naboru konstant. V drugem pa podamo ime metode. Ime metode je lahko zgolj ime metode, lahko pa je polno ime, ki vsebuje tudi razred, v katerem se metoda nahaja. Če podamo zgolj ime metode, v naboru konstant pa obstajata dve metodi z istim razredom, se uporabi prva metoda, ki je najdena v naboru konstant. 

