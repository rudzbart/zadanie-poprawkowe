# Opis aplikacji

Moja aplikacja webowa służy do skracania linków.  
Aplikacja łączy się z dwoma zewnętrznymi *API*.  
Z jednym z nich w celu utworzenia skróconego linku z linku podanego przez użytkownika  
oraz drugim z nich, aby otrzymany skrócony link przedstawić przy pomocy kodu QR.    
  
Zależności, które wykorzystałem do stworzenia aplikacji to: **Spring Web** oraz **Thymeleaf**.  
**Spring Web** jest podstawową zależnością potrzebną do tworzenia aplikacji w Springu,  
natomiast **Thymeleaf** jest silnikiem szablonów, z którego korzystałem do wykonania warstwy graficznej projektu.

### Opis repozytorium

Cała ścieżka **.mvn/wrapper** to pierwszą rzeczą, która widnieje w repozytorium.  
Zawartość folderu **wrapper** to pliki pochodzące od *Apache Maven* czyli narzędzia automatyzującego budowę na platormę Java.  
Pobierają one odpowiednią wersję *Maven* i pozwalają uruchomić program mimo, że narzędzia nie ma na naszej maszynie.  
  
Kolejnym wartym wspomnienia katalogiem jest oczywiście katalog **main**, z którego przechodzimy następnie do katalogu z nazwą projektu  
lub katalogu **resources**.
  
W pierwszym katalogu widoczne są *pakiety* programu tj.: **controller**, **model**, **view**.  

#### Linkshortener
##### Model
Plik znajdujący się w katalogu **model** czyli *LinkShortener* to klasa, która powstała przez stronę *json2pojo*  
i zawiera przekszatłcony odpowiednio schemat odpowiedzi *API* na zapytanie o skrócony link.


##### Controller
W pierwszej kolejności zauważymy tutaj klasę *LinkShortenerApiClient*, która jak sama nazwa wskazuje jest klientem *API* i służy do łączenia się z nim.
Na początku klasy tworzymy obiekt typu *RestTemplate* pozwalający na komunikację z *API*. Dzięki niemu możemy wysyłać dane z metodą *POST* lub odbierać je z metodą *GET*.  
Kolejna jest metoda *getShortenedUrl*. Metoda posiada parametr, którym jest link podany przez użytkownika w interfejsie graficznym aplikacji.  
Podany link jest przekazywany jako *body* w metodzie *POST* wykonywanej dalej, jest w niej również przekazywany z góry ustalony link do *API* w formie *final String url*,  
przekształconego dalej w obiekt *URI*.  
Wszystko wysyłane jest przy pomocy wcześniej wspomnianego *RestTemplate* a dokładniej jego metody *postForEntity*.  
Na koniec zwracamy nasz skrócony *String* przy odpowiedniej jego edycji (stąd wynikają metody *substring* oraz *replace*).

Znajdziemy tutaj również *LinkShortenerService* czyli bardzo prosty interfejs z metodą *getShortenedUrl*, która będzie wykorzystywana w implementacji tego interfejsu.
  
*LinkShortenerServiceImplementation* jest klasą implementująca opisany wyżej interfejs. Wstrzyknięty jest tutaj także wcześniej wspominany *ApiClient*.  
To właśnie ta klasa będzie wstrzyknięta do naszej klasy *View*, przez nią przechodzą wszystkie operacje wykonywane w aplikacji.  
Na temat znajdującej się tutaj metody nie można tak naprawdę napisać nic ciekawego ponieważ została ona stworzona realnie w w klasie *ApiClient*  
a tutaj tylko ją wywołujemy.  
Cały ten nieco bezsensowny na pierwszy rzut oka zabieg ma w jakimś małym stopniu pokazać sposób budowy większych aplikacji webowych oraz pakietowania  
w wzorcu MVC.

##### View
Widoczna tu klasa - *ViewController* odpowiedzialna jest za wyświetlanie odpowiednich plików *html* z katalogu **resources** na odpowiednich *endpointach*,  
dodawanie atrybutów, z których korzystać będzie **Thymeleaf** oraz tworzenia kodu *QR* ze skróconego linku.  
Jest tutaj oczywiście wstrzyknięta klasa *LinkShortenerServiceImplementation*, komunikująca się z klientem *API*.  
Pierwsza znajdująca się tutaj metoda sprowadza się do wyświetlania pliku **home.html**,  
druga natomiast służy wyświetleniu tego samego pliku ale tym razem ze skróconym linkiem.

#### Resources

##### Static
Ten folder zawiera podfoldery *css* oraz *scripts*.  
W pierwszym znajduję się szablon stylów wykorzystywany w pliku **home.html**, w drugim plik skryptów *js* wykorzystywany w tym samym miejscu.
Skrypty w pliku *js* służą do:  
1. Kopiowania skróconego linku do schowka przy pomocy przycisku  
2. Ujawniania kodu *QR*

##### Templates
Tu znajdziemy plik **home.html**, czyli to co najważniejsze z perspektywy użytkownika.
Plik wykorzystuje wcześniej wspomnianego **Thymeleafa**, arkusz stylów *styles.css*, plik skryptów *scripts.js*  
oraz dodatkowy arkusz stylów *bootstrapa*, który nieco poprawia estetykę aplikacji.
