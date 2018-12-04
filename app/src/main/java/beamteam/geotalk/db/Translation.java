package beamteam.geotalk.db;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity (tableName = "translations", primaryKeys = {"phraseID", "language"})
public class Translation {

    @NonNull
    public String translation;
    public int phraseID;
    @NonNull
    public String language;

    public Translation(int phraseID, String language, String translation) {
        this.translation = translation;
        this.phraseID = phraseID;
        this.language = language;
    }

    public static Translation[] getInitialData() {
        return new Translation[] {

                new Translation(0, "Korean", "탑승권"),
                new Translation(0, "English", "baggage claim"), // baggage claim

                // General Korean
                new Translation(1, "Korean", "네"),
                new Translation(2, "Korean", "아니요"),
                new Translation(3, "Korean", "제발"),
                new Translation(4, "Korean", "고맙습니다"),
                new Translation(5, "Korean", "안녕하세요"),
                new Translation(6, "Korean", "안녕히가세요"),
                new Translation(7, "Korean", "제 이름은"),
                new Translation(8, "Korean", "이름은?"),
                new Translation(9, "Korean", "저분 이름은?"),
                new Translation(10, "Korean", "어디에서 왔어요?"),
                new Translation(11, "Korean", "에서 왔어요"),
                new Translation(12, "Korean", "몇개?"),

                new Translation(13, "Korean", "일/하나"),
                new Translation(14, "Korean", "이/둘"),
                new Translation(15, "Korean", "삼/셋"),
                new Translation(16, "Korean", "사/넷"),
                new Translation(17, "Korean", "오/다섯"),
                new Translation(18, "Korean", "육/여섯"),
                new Translation(19, "Korean", "칠/일곱"),
                new Translation(20, "Korean", "팔/여덟"),
                new Translation(21, "Korean", "구/아홉"),
                new Translation(22, "Korean", "열"),
                new Translation(23, "Korean", "백"),
                new Translation(24, "Korean", "천"),
                new Translation(25, "Korean", "만"),

                new Translation(26, "Korean", "월요일"),
                new Translation(27, "Korean", "화요일"),
                new Translation(28, "Korean", "수요일"),
                new Translation(29, "Korean", "목요일"),
                new Translation(30, "Korean", "금요일"),
                new Translation(31, "Korean", "토요일"),
                new Translation(32, "Korean", "일요일"),
                new Translation(33, "Korean", "시"),
                new Translation(34, "Korean", "분"),
                new Translation(35, "Korean", "초"),
                new Translation(36, "Korean", "주"),
                new Translation(37, "Korean", "월"),
                new Translation(38, "Korean", "연"),
                new Translation(39, "Korean", "몇시 예요?"),

                new Translation(40, "Korean", "... 어디에요?"),
                new Translation(41, "Korean", "길 잃었어요"),
                new Translation(42, "Korean", "좌"),
                new Translation(43, "Korean", "우"),
                new Translation(44, "Korean", "앞"),
                new Translation(45, "Korean", "위"),
                new Translation(46, "Korean", "아래"),

                new Translation(47, "Korean", "어떤 버스 타요?"),
                new Translation(48, "Korean", "어떤 선 타요?"),
                new Translation(49, "Korean", "... 로 가요?"),
                new Translation(50, "Korean", "___ 역이 어디에요"),
                new Translation(51, "Korean", "어디에서 표사요"),

                new Translation(52, "Korean", "... 로 가주세요"),

                new Translation(53, "Korean", "얼마에요?"),
                new Translation(54, "Korean", "원"),

                new Translation(55, "Korean", "제 ... 를 잃었어요"),

                new Translation(56, "Korean", "이게 뭐예요?"),
                new Translation(57, "Korean", "이 뭐예요?"),
                new Translation(58, "Korean", "어떻게 말해요?"),

                new Translation(59, "Korean", "가까운 식당 어디에요?"),
                new Translation(60, "Korean", "배고파요"),
                new Translation(61, "Korean", "아침"),
                new Translation(62, "Korean", "점심"),
                new Translation(63, "Korean", "저녁"),
                new Translation(64, "Korean", "물주세요?"),
                new Translation(65, "Korean", "이거 더주세요?"),
                new Translation(66, "Korean", "저는 채식주의자 예요"),
                new Translation(67, "Korean", "___ 못먹어요"),
                new Translation(68, "Korean", "___ 알러지 있어요"),
                new Translation(69, "Korean", "이거 안에 ___ 있어요?"),

                // General English
                new Translation(1, "English", "Yes"),
                new Translation(2, "English", "No"),
                new Translation(3, "English", "Please"),
                new Translation(4, "English", "Thank you"),
                new Translation(5, "English", "Hello"),
                new Translation(6, "English", "Goodbye"),
                new Translation(7, "English", "My name is"),
                new Translation(8, "English", "What is your name?"),
                new Translation(9, "English", "What is their name?"),
                new Translation(10, "English", "Where are you from"),
                new Translation(11, "English", "I am from"),
                new Translation(12, "English", "How many?"),
                new Translation(13, "English", "One"),
                new Translation(14, "English", "Two"),
                new Translation(15, "English", "Three"),
                new Translation(16, "English", "Four"),
                new Translation(17, "English", "Five"),
                new Translation(18, "English", "Six"),
                new Translation(19, "English", "Seven"),
                new Translation(20, "English", "Eight"),
                new Translation(21, "English", "Nine"),
                new Translation(22, "English", "Ten"),
                new Translation(23, "English", "One hundred"),
                new Translation(24, "English", "Thousand"),
                new Translation(25, "English", "Million"),

                new Translation(26, "English", "Monday"),
                new Translation(27, "English", "Tuesday"),
                new Translation(28, "English", "Wednesday"),
                new Translation(29, "English", "Thursday"),
                new Translation(30, "English", "Friday"),
                new Translation(31, "English", "Saturday"),
                new Translation(32, "English", "Sunday"),
                new Translation(33, "English", "Hours"),
                new Translation(34, "English", "Minutes"),
                new Translation(35, "English", "Seconds"),
                new Translation(36, "English", "Week"),
                new Translation(37, "English", "Month"),
                new Translation(38, "English", "Year"),
                new Translation(39, "English", "What time is it?"),

                new Translation(40, "English", "Where is ?"),
                new Translation(41, "English", "I am lost"),
                new Translation(42, "English", "Left"),
                new Translation(43, "English", "Right"),
                new Translation(44, "English", "Go straight"),
                new Translation(45, "English", "Up"),
                new Translation(46, "English", "Down"),

                new Translation(47, "English", "Which bus?"),
                new Translation(48, "English", "Which train?"),
                new Translation(49, "English", "Does this go to?"),
                new Translation(50, "English", "Where is the __ station?"),
                new Translation(51, "English", "Where do I buy tickets?"),
                new Translation(52, "English", "Take me to __"),

                new Translation(53, "English", "How much?"),
                new Translation(54, "English", "Currency"),

                new Translation(55, "English", "I lost my __"),
                new Translation(56, "English", "What is this?"),
                new Translation(57, "English", "What is ___ ?"),
                new Translation(58, "English", "How do you say __ ?"),

                new Translation(59, "English", "Where is the nearest restaurant?"),
                new Translation(60, "English", "I am hungry"),
                new Translation(61, "English", "Breakfast"),
                new Translation(62, "English", "Lunch"),
                new Translation(63, "English", "Dinner"),
                new Translation(64, "English", "May I have water?"),
                new Translation(65, "English", "Can I have more of this?"),
                new Translation(66, "English", "I am vegetarian"),
                new Translation(67, "English", "I do not eat __ "),
                new Translation(68, "English", "I have this allergy"),
                new Translation(69, "English", "Does this have __ in it?"),
        };
    }

}
