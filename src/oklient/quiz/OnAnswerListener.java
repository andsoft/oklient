package oklient.quiz;

import objects.Question;

public interface OnAnswerListener {
	public abstract void onAnswer(Question quest);
}
