package com.andres.aoran;

import com.andres.aoran2.R;

public class QuizBook {

    public static String[] questions = new String [] {
            "book", "teacher", "aquarium", "desk", "eraser", "umbrella",
            "pencil", "ruler", "insect", "origami",
    };

    public static int[] images = new int [] {
            R.drawable.abook, R.drawable.ateacher, R.drawable.anaquarium,
            R.drawable.adesk, R.drawable.aneraser, R.drawable.anumbrella,
            R.drawable.apencil, R.drawable.aruler, R.drawable.aninsect, R.drawable.anorigami
    };

    public static int[] questionSound = new int []{
            R.raw.book, R.raw.teacher,R.raw.aquarium,
            R.raw.desk,R.raw.eraser,R.raw.umbrella,
            R.raw.pencil,R.raw.ruler, R.raw.insect,R.raw.origami
    };
    public static boolean[] answers = new boolean[]{
            true,true,false,true,false,false,true,true,false,false
    };
}