package com.tamplleer.testrussian.activities.main.components;

import android.app.Activity;
import android.content.Intent;

import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.activities.result.EndGame;
@Deprecated
public class LetterSubmitter {
/*    public void next() {
        statistic.setAnswerRight(letterChange.isLetterColor());
        letterChange.setLetterColor(false);
        statistic.setSteps(statistic.getSteps() + 1);
        statistic.setSelectWordNumber(statistic.getSelectWordNumber() + 1);
        handler.post(() -> score.setText(statistic.getSteps() + 1 + "/" + statistic.getLengthInScore()));
        if (statistic.isAnswerRight()) {
            constraintLayout.setBackgroundResource(R.drawable.background);
            statistic.setWinResult(statistic.getWinResult() + 1);
            t.setTextColor(context.getResources().getColor(R.color.DarkSlateGray));
            if (rightStrike > 0) audio.playSound(audio.getSoundNumber("rightMore"));
            else audio.playSound(audio.getSoundNumber("right"));
            rightStrike++;
            statistic.setAnswerInArray(true,statistic.getSteps() - 1);
            animationObject.standUp(t);


        } else {
            t.setTextColor(context.getResources().getColor(R.color.Khaki));
            animationObject.shake(((Activity) context).findViewById(R.id.text));
            audio.playSound(audio.getSoundNumber("wrong"));
            rightStrike = 0;
            constraintLayout.setBackgroundResource(R.drawable.background_red_2);
            statistic.setAnswerInArray(false,statistic.getSteps() - 1);
        }


        t.setText("" + statistic.getWordToScreen());

        if (statistic.getSteps() == statistic.getLengthInScore()) {
            Intent intent = new Intent(context,
                    EndGame.class);
            intent.putExtra("rightAnswer", statistic.getWinResult());
            intent.putExtra("arrayRight", statistic.getAnswerArray());
            intent.putExtra("lengthInScore", statistic.getLengthInScore());
            intent.putExtra("wordArray", statistic.getWordMassive());
            context.startActivity(intent);
            ((Activity) context).finish();

        } else {
            letterChange.setLetterColor(statistic.isAnswerRight());
            statistic.setWordToScreen(makeWord.showWord(statistic.getSelectWordNumber()));
        }
        bmnext.setClickable(false);

    }*/
}
