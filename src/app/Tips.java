package app;

import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;

import java.awt.*;

public class Tips extends Component {
    private JTipOfTheDay porady;

    public Tips() {
        initGUI();
    }

    public void initGUI() {
        porady = new JTipOfTheDay(tipModel());
    }

    public void showTips() {
        porady.showDialog(null); // Używamy 'null', aby dialog był wyśrodkowany na ekranie
    }

    public DefaultTipModel tipModel() {
        DefaultTipModel tips = new DefaultTipModel();
        tips.add(new DefaultTip("Porada 1",
                "Identyfikacja mocnych i słabych stron programowania może pomóc Ci znaleźć konkretne obszary, w których jesteś biegły i obszary, w których możesz się ulepszyć."));
        tips.add(new DefaultTip("Porada 2",
                "Kursy programowania to świetny sposób na rozwój umiejętności programowania. Kursy programowania zazwyczaj koncentrują się na jednym języku programowania i uczą, jak tworzyć programy, które możesz zbudować w ramach swojej pracy."));
        tips.add(new DefaultTip("Porada 3",
                "Certyfikaty programistyczne pokazują potencjalnym pracodawcom, że posiadasz biegłość w określonym języku programowania. Aby przygotować Cię do egzaminu, podmiot certyfikujący może udostępnić Ci materiały do ​​nauki, dzięki którym możesz dowiedzieć się więcej o języku programowania."));
        tips.add(new DefaultTip("Porada 4",
                "Grupy programistyczne to zespoły innych programistów, którzy wspólnie pracują nad rozwojem projektów. Mogą pracować nad różnymi aspektami tego samego projektu lub pomagać sobie nawzajem przy poszczególnych projektach."));
        tips.add(new DefaultTip("Porada 5",
                "Korzystając z listy umiejętności i technik, które chcesz rozwinąć, utwórz harmonogram ćwiczeń. Określ, kiedy i jak długo będziesz pracować nad swoimi umiejętnościami programowania, co pomoże Ci ustrukturyzować sesje ćwiczeń."));
        tips.add(new DefaultTip("Porada 6",
                "Konsekwentna praktyka jest kluczem do rozwijania umiejętności kodowania."));
        tips.add(new DefaultTip("Porada 7",
                "Wyznaczanie osiągalnych celów pomaga zachować motywację i koncentrację, a także poczucie spełnienia w miarę postępów w kodowaniu"));
        tips.add(new DefaultTip("Porada 8",
                "Uczenie się od innych to doskonały sposób na zdobycie nowych spostrzeżeń i pomysłów."));
        tips.add(new DefaultTip("Porada 9",
                "Praca nad praktycznymi projektami pomaga zastosować zdobytą wiedzę i rozwijać umiejętności kodowania."));
        tips.add(new DefaultTip("Porada 10",
                "Dzielenie złożonych problemów na mniejsze, łatwiejsze do zarządzania części ułatwia ich rozwiązanie."));
        tips.add(new DefaultTip("Porada 11",
                "Umiejętności debugowania są niezbędne w kodowaniu."));
        tips.add(new DefaultTip("Porada 12",
                "Czytanie kodu napisanego przez innych to doskonały sposób na poznanie nowych technik i podejść do kodowania."));
        tips.add(new DefaultTip("Porada 13",
                "Robienie przerw pomaga naładować baterie i zachować świeżość."));
        tips.add(new DefaultTip("Porada 14",
                "Bycie na bieżąco z najnowszymi językami, narzędziami i technikami programowania pomaga zachować aktualność i konkurencyjność."));
        return tips;
    }


}
