package com.tamplleer.testrussian;

import android.content.res.AssetManager;
import android.media.SoundPool;
import android.widget.TextView;

/**
 * Created by tampl on 22.01.2018.
 */

public class S {
    public static boolean click = false;
    public static boolean someClick = false;
    public static int changeWord = 0;
    public static String wordtoscreen = "";
    public static String stringTOchar = "";
    public static TextView charTextV;
    public static int delet = 0;
    public static int type;
    public static int lengsInScore;
    public static int steps=0;
    public static int win = 0;
    public static boolean right = false;
    public static boolean clickWord = false;
    public static boolean pause = true;
    public static int pravi = 0;
    public static int x = 0;
    public static int selectActivity=0;


    public static SoundPool mSoundPool;
    public static AssetManager mAssetManager;
    public static int mCatSound, winSound,winALL, prav, praveso, neprav,anvilSound,aS,iS,eS,oS,yS,ieS,iaS,iyS,ieaS,hor,nedovol;
    public static int mStreamID;


    public static String allWord =
            ",агЕнт,алфавИт,анАтом,асимметрИя,аэропОрты,бАнты,бУнгало,балОванный,балУясь,баловАть,блАговест,блуднИца,бралА,бралАсь,бухгАлтеров,вЕрба,вЕчеря,вОвремя,вОгнутый,валовОй,вандАлы,вдовствО,вернА,вероисповЕдание,ветеринАрия,взАпуски,взапертИ,взялА,взялАсь,включЁн,включЁнный,включИм,включИт,включИшь,влилАсь,водопровОд,воздухопровОд,ворвалАсь,воспринялА,воспроизведЕние,воссоздалА,вручИт,гЕнезис,гЕрбовый,газопровОд,гналА,гналАсь,граждАнство,грошОвый,дОверху,дОгмат,дОнизу,дОсуха,дОсыта,давнИшний,дефИс,диспансЕр,добелА,добралА,добралАсь,довезЁнный,договОр,договорЁнность,дождалАсь,дозИровать,дозвонИтся,дозвонЯтся,докраснА,докумЕнт,донЕльзя,досУг,дотрОнуться,дремОта,духовнИк,еретИк,жалюзИ,ждалА,жилОсь,зАгнутый,зАгодя,зАнял,зАняло,зАнятый,зАсветло,зАтемно,завИдно,задОлго,закУпорив,закУпорить,занятА,запертА,запломбировАть,заселЁн,звалА,звонИм,звонИт,звонИшь,зимОвщик,злОба,знАмение,знАчимость,знАчимый,зубчАтый,Издавна,Иконопись,Иксы,Искоса,Исстари,игУмен,идеОлог,иерОглиф,изОгнутый,избалОванный,избаловАть,издрЕвле,изобретЕние,импЕрский,инАче,инсУльт,инстИнкт,исключИт,искривИться,исчЕрпать,кАмбала,кАшлянуть,кОнусы,кУхонный,каталОг,каучУк,квартАл,кедрОвый,клАла,клЕить,коклЮш,корЫсть,кормЯщий,крАлась,крАны,красИвее,красИвейший,кремЕнь,кренИтся,кровоточАщий,кровоточИть,лЕкторы,лилА,лилАсь,ловкА,ломОта,ломОть,лубОчный,лыжнЯ,мЕльком,мЕстностей,мозаИчный,молОх,молЯщий,монолОг,мусоропровОд,мытАрство,нАискось,нАчал,нАчали,нАчатый,нЕдруг,нЕнависть,нЕнецкий,нОвости,новостЕй,нОгтя,наОтмашь,навЕрх,навралА,наговОр,надОлго,наделИт,надорвалАсь,нажИвший,нажитА,назвалАсь,назлО,накренИт,налИвший,налилА,намЕрение,нанЯвшийся,нарОст,нарвалА,насорИт,начАв,начАвший,начАвшись,начАть,началА,недУг,незадОлго,некролОг,ненадОлго,несказАнно,нефтепровОд,низИна,низведЁн,новоприбЫвший,новорождЁнный,обеспЕчение,обетовАнный,обзвонИт,облегчИт,облегчИть,облилАсь,обнаружЕние,обнялАсь,обогналА,ободрЁн,ободрЁнный,ободрИть,ободрИшься,ободралА,ободренА,обострЁнный,обострИть,объезднОй,одОбренный,одолжИт,озвУчение,озлОбить,озлОбленный,ознакОмленный,оклЕить,окружИт,опОшлят,оперИться,опломбировАть,определЁн,оптОвый,освЕдомить,освЕдомиться,осведомлЁнный,остриЁ,осужденА,отбылА,отдАв,отдалА,откУпорил,откУпорить,отключЁнный,отозвалА,отозвалАсь,оторвалА,Отрочество,оценЁнный,пАсквиль,пЕтля,пОнял,пОручни,пОстриг,пУстошь,пУстынь,патриАрхия,перезвонИт,перекрОенный,перелилА,плЕсневеть,платО,плодоносИть,пломбировАть,поИмка,повторЁнный,повторИт,поделЁнный,подзаголОвок,поднЯв,подрОстковый,подчистУю,позвалА,позвонИт,позвонИшь,поискОвый,полилА,положИл,положИть,полтергЕйст,понЯв,понЯвший,понялА,портфЕль,послАла,прИбыл,прИбыло,прИкус,прИнял,прИняли,прИнятый,предвосхИтить,премировАть,прибЫв,прибылА,приговОр,придАное,принУдить,принЯть,прогИб,прожИвший,прозорлИва,процЕнт,псевдонИм,пулОвер,пургА,путепровОд,рАджа,рАпорт,рОвненько,рОзги,развитОй,ракУшка,рвалА,РевЕнь,сЕтчатый,сОгнутый,сОздало,сабО,свЁкла,свЁкла,сверлИт,сверлИшь,сегмЕнт,сирОты,слИвовый,снялА,созЫв,создАвший,создалА,созданА,сорИт,сосредотОчение,срЕдства,срЕдствами,стАтуя,столЯр,тАинство,тОртов,тОрты,тОтчас,тУфля,табУ,тамОжня,танцОвщица,тигрОвый,толИка,тошнотА,трУбчатый,трубопровОд,убралА,убыстрИть,углубИть,уговОр,узаконЕние,украИнский,укрепИт,умЕрший,упрОчение,факсИмиле,фетИш,флюорогрАфия,ходАтайство,цЕнтнер,цемЕнт,цепОчка,чЕрпать,чИстильщик,шАрфы,шофЁр,щЁлкать,щИколотка,щавЕль,щемИт,экспЕрт,Экскурс,электропрОвод";
}
