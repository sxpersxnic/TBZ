    import java.util.List;

    public class IntialPersons
    {
        public List<Person> intitalizePersons()
        {
            Person tristan = new Person("Tristan", false);
            Person lars = new Person("Lars", false);
            Person erion = new Person("Erion", false);
            Person lukas = new Person("Lukas", false);
            Person jean_marc = new Person("Jean-Marc", true);
            Person miguel = new Person("Miguel", true);
            Person silvan = new Person("Silvan", true);
            Person derian = new Person("Derian", false);
            Person bryan = new Person("Bryan", false);
            Person mara = new Person("Mara", false);
            Person dimitri = new Person("Dimitri", false);
            Person kumaran = new Person("Kumaran", false);
            Person januscha = new Person("Januscha", true);
            Person pablo = new Person("Pablo", true);
            Person christian = new Person("Christian", false);
            Person iljas = new Person("Iljas", false);
            Person matheus = new Person("Matheus", false);

            tristan.setFriends(List.of(lars, erion, lukas, jean_marc, miguel, mara, dimitri, bryan));
            lars.setFriends(List.of(tristan, erion, lukas, jean_marc, miguel, silvan, derian, kumaran, matheus, januscha));
            erion.setFriends(List.of(tristan, lars, iljas));
            lukas.setFriends(List.of(tristan, lars, pablo, jean_marc, miguel));
            jean_marc.setFriends(List.of(tristan, lars, miguel, christian, silvan, derian, kumaran, lukas));
            miguel.setFriends(List.of(tristan, lars, jean_marc, silvan, derian, kumaran, lukas));
            silvan.setFriends(List.of(lars, miguel, jean_marc, derian, kumaran));
            derian.setFriends(List.of(lars, silvan, miguel, jean_marc, kumaran));
            bryan.setFriends(List.of(mara, dimitri));
            mara.setFriends(List.of(dimitri, bryan));
            dimitri.setFriends(List.of(bryan, mara));
            kumaran.setFriends(List.of(lars, silvan, miguel, jean_marc, derian));
            januscha.setFriends(List.of(lars));
            pablo.setFriends(List.of(lukas));
            christian.setFriends(List.of(jean_marc));
            iljas.setFriends(List.of(erion));
            matheus.setFriends(List.of(lars));

            return List.of(
                    tristan, lars, erion, lukas, jean_marc, miguel, silvan, derian,
                    bryan, mara, dimitri, kumaran, januscha, pablo, christian, iljas, matheus
            );
        }
    }
