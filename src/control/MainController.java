package control;

import model.List;
import model.Person;

public class MainController {

    private List<Person> allPersons;
    private String[] names = {"Alsbach", "Bachmann", "Cyrus", "Davidoff", "Eregon", "Füller","Giesehau","Halidsch","Irimoff","Zylla","Yilderim","Lupp","Schein","Xenomo","Iwan","Ali","Kötter","Kleinhans","Diablo","Overwatch","Starcraft","Warcraft","Minecraft","Krunker","Command","And","Conquer","Path","Exile","Valheim"};

    public MainController(int amount){
        allPersons = new List<>();
        for(int i = 0; i < amount; i++){
            allPersons.append(createPerson());
        }
    }

    private Person createPerson(){
        Person p = new Person(getRandomName());
        return p;
    }

    public String getRandomName(){
        return names[(int)(Math.random()*names.length)];
    }

    public String showList(){
        String output = "Ausgabe: ";
        allPersons.toFirst();
        while(allPersons.hasAccess()){
            output = output + allPersons.getContent().getName()+" ("+allPersons.getContent().getBirthdate()+"), ";
            allPersons.next();
        }
        return output;
    }

    /**
     * Schreibe einen Algorithmus zum Suchen einer Person innerhalb einer Liste. Falls die Person gefunden wurde, gib ihren Namen samt Geburtsdatum aus.
     * @param name
     * @return
     */
    public String searchList(String name){
        String output = "Nicht gefunden.";
        allPersons.toFirst();
        while(allPersons.hasAccess()){
            if(allPersons.getContent().getName() == name){
                output = "Name: " + name + "Geburtstag: " + allPersons.getContent().getBirthdate();
            }
            allPersons.next();
        }
        return output;
    }

    /**
     * Stortiere die Liste nach Namen (nicht nach Geburtsdatum!). Entscheide dich hierzu für einen Sortieralgorithmus.
     * Gib an, ob deine Umsetzung stabil ist und ob sie in-place ist.
     */
    public void sortList(){
        allPersons = bubbleSort(allPersons);
    }

    public List<Person> bubbleSort(List<Person> toBeSortedList){

        List<Person> sortedList = new List<>();
        Person i;

        while(!toBeSortedList.isEmpty()){

            toBeSortedList.toFirst();
            i = toBeSortedList.getContent(); // i ist das vorherige Objekt welches wir prüfen ob es größer ist als das nächste
            toBeSortedList.next();

            while(toBeSortedList.hasAccess()){
                //wir sind auf dem Objekt nach i und prüfen deswegen kleiner 0
                if(toBeSortedList.getContent().getName().compareTo(i.getName()) < 0){
                    //wir wollen hinter dem nächsten also nach dem jetzigen Objekt i reinfügen so das wir swappen
                    toBeSortedList.next();
                    if(toBeSortedList.hasAccess()) {
                        toBeSortedList.insert(i);
                    }else {
                        // das nächste objekt existiert nicht
                        // also hatten wir gerade das letzte Objekt, welches nach dem i ist was bedeutet
                        // wir können einfach appenden und so wäre i nach dem jetztigen Objekt
                        toBeSortedList.append(i);
                    }
                    // wir müssen zurück zum ursprünglichem i, welches removed werden soll damit wir einen swap haben
                    getObject(toBeSortedList,i);
                    toBeSortedList.remove();
                    // wir wollen zum nächsten i wofür wir einmal nexten müssen
                    toBeSortedList.next();
                    i = toBeSortedList.getContent();
                }else{
                    //wir haben wir nichts wir gehen weiter
                    i = toBeSortedList.getContent();
                    toBeSortedList.next();
                }
            }
            //das größte objekt von der restlichen liste ist
            // am ende jetzt müssen wir einfach es am anfang dranhängen
            toBeSortedList.toLast();
            sortedList.toFirst();
            sortedList.insert(toBeSortedList.getContent());
            toBeSortedList.remove();
        }


        return sortedList;
    }

    public void getObject(List<Person> list, Person person) {
        list.toFirst();
        while(list.hasAccess()){
            if(list.getContent() == person){
                return;
            }
            list.next();
        }
    }
}
