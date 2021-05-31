package com.jansmoneymachine.timetablestudents.Main;

import com.jansmoneymachine.timetablestudents.R;

public class CardItemDatabase {

    private final static CardItem[] cardItems = {
            new CardItem("Time Table", "Well organized and good looking - your own time table.", R.drawable.timetable_icon),
            new CardItem("Deadlines", "All your deadlines at a glance.", R.drawable.deadline_icon),
            new CardItem("Notes", "Take notes - simple, fast and easy.", R.drawable.notes_icon),
            new CardItem("Grocery List", "Never going to forget anything in the supermarket again.", R.drawable.grocery_icon),
            new CardItem("Daniel Jung", "Need fast and easy help with maths? Daniel Jung is there for you.", R.drawable.youtube_icon)
    };


    public static CardItem[] getCardItems() {
        return cardItems;
    }
}
