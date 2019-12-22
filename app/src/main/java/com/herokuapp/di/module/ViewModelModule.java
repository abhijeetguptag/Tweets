package com.herokuapp.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.herokuapp.data.entity.Post;
import com.herokuapp.viewmodel.AuthorsViewModel;
import com.herokuapp.viewmodel.CommentsViewModel;
import com.herokuapp.viewmodel.PostViewModel;
import com.herokuapp.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthorsViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsAuthorsViewModel(AuthorsViewModel authorsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CommentsViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsCommentsViewModel(CommentsViewModel commentsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsAPostsViewModel(PostViewModel postsViewModel);

    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
