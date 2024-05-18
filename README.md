Projektowanie aplikacji – Projekt (Java)  
Zadanie 1 – Interfejs GUI  
Korzystając  z  biblioteki  Swing  zbudować  aplikację  realizując  następujące
zadania:
1. Utworzyć  nową  aplikację  (klasa  JFrame),  określić  rozmiar  i  położenie  na  środku
   ekranu, opisać zdarzenie zamknięcia okna.
2. Zdefiniować menu (JMenu, JMenuItem), pasek narzędziowy (JToolBar), pasek statusu
   (JPanel) oraz okna z kontekstem pomocy i informacją o autorze (JDialog). Zawartość
   uzupełniać w trakcie rozwoju aplikacji. 
3. W centralnej części aplikacji umieścić następujące elementy:
  - etykiety tekstowe (JLabel),  
  - pole tekstowe (JTextField) opisujące wprowadzaną do tablicy liczbę,  
  - elementy  określające  pozycję  wiersza  i  kolumny  wybranej  komórki  tabeli
  (JSpinner dla parzystego i JSlider dla nieparzystego numeru indeksu),  
  - tabelę o rozmiarze 5x5 (JTable)  
  - przyciski  (JButton)    realizujące  funkcję  zerowania  tablicy,  umieszczenia
  wartości z pola tekstowego do komórki tablicy, i zapis jej zawartości do pliku.
  - Przestrzeń tekstową (JTextArea) zawierającą wyniki obliczeń. 
 4. Opisać zdarzenia realizowane przez metodę akcji (interfejs ActionListener). Zdarzenia
  powinny być uruchamiane za pomocą przycisków, menu i paska narzędziowego. 
5. Przekształcić napis z pola tekstowego na liczbę. Za pomocą instrukcji try {} catach {}
  zabezpieczyć  program  przed  błędami.  Wykorzystać  okno  dialogowe  do  informacji o błędach (JOptionPane).
  6. Dodać pole wyboru (JList dla ostatniej cyfry indeksu równej wartości z przedziału 0-4
    i JComboBox dla wartości 5-9) z trzema elementami, a wyniki umieścić w przestrzeni
    tekstowej.  
- a. Suma elementów - obliczenie sumy wszystkich elementów tablicy 
- b. Średnia elementów – obliczenie średniej z wszystkich elementów tablicy 
- c. Wartość max i min – znalezienie liczby z największą i najmniejszą wartością.  