package com.herokuapp.di.builder;

import com.herokuapp.view.fragment.AuthorFragment;
import com.herokuapp.view.fragment.CommentFragment;
import com.herokuapp.view.fragment.PostsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract AuthorFragment contributeAuthorFragment();

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract PostsFragment contributePostFragment();


    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract CommentFragment contributeCommentFragment();

}
