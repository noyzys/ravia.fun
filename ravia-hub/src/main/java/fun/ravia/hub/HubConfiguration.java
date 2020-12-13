package fun.ravia.hub;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class HubConfiguration {

    public String gameModeCommandUsageMessage = "Poprawne uzycie: /gamemode [0|1|2|3].";
    public String gameModeCommandModeChangedMessage = "Pomyslnie zmieniono tryb gry na: {GAMEMODE}.";
    public String kickAllCommandUsageMessage = "Poprawne uzycie: /kickall [reason].";

    public String whiteListCommandUsageMessage = "Poprawne uzycie: /whitelist [on|off|add|remove|list|clear] [player]";
    public String whiteListCommandWhiteListIsEnabledDisabledMessage = "WhiteList'a jest juz: ";
    public String whiteListCommandWhiteListSetStatusMessage = "WhiteList'a zostala: ";
    public String whiteListCommandPlayerIsAddedToWhiteListMessage = "Podany gracz jest juz dodany na whitelist'e.";
    public String whiteListCommandPlayerAddedToWhiteListMessage = "Pomyslnie dodano gracza: {PLAYER} na whitelist'e.";
    public String whiteListCommandPlayerIsRemovedWithWhiteListMessage = "Podany gracz nie jest juz na whitelist.";
    public String whiteListCommandPlayerRemovedWithWhiteListMessage = "Pomyslnie usunieto gracza: {PLAYER} z whitelist'y.";
    public String whiteListCommandPersonsOnWhiteListMessage = "Osoby dodane na whitelist'e: {LIST}.";
    public String whiteListCommandCleanedListsWithWhiteListMessage = "Pomyslnie wyczysciles osoby dodane na whitelist'e.";
    public String whiteListDefaultReasonMessage = "{N}Serwer obecnie jest w trakcie tworzenia..{N}";

    public List<String> joinMessages = Collections.singletonList("Witaj: {PLAYER} na ravia.fun");

    public int defaultSlotsOnTheServer = 200;
    public String serverIsFullMessage = "{N}Serwer obecnie jest pelny{N}Wpuszczamy stopniowe prosze zaczekac.{N}";

    public boolean whiteListStatus;
    public List<String> whiteListByPassList = Arrays.asList(
            "vFeesT",
            "MajNik");

    public boolean isWhiteListStatus() {
        return whiteListStatus;
    }

    public void setWhiteListStatus(final boolean whiteListStatus) {
        this.whiteListStatus = whiteListStatus;
    }
}
