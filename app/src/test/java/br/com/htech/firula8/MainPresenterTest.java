package br.com.htech.firula8;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import br.com.htech.firula8.Main.MainContract;
import br.com.htech.firula8.Main.MainPresenter;
import br.com.htech.firula8.Modelo.Shot;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by arturhayne on 03/02/2018.
 */

public class MainPresenterTest {

    private List<Shot> SHOTS ;

    @Mock
    private Context context;

    @Mock
    private MainContract.View view;

    private MainPresenter mainPresenter;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
        mainPresenter = new MainPresenter(view,context);
        SHOTS = new ArrayList<>();
        Shot s = new Shot();
        s.setTitle("teste");
        s.setDescription("Teste");
        SHOTS.add(s);
        SHOTS.add(s);
        SHOTS.add(s);

    }

    @Test
    public void iniciarMain(){
       // mainPresenter.carregarShots();
        // Then add note UI is shown
        verify(view).showShots(SHOTS);
        //verify(view).showProgressBar(false);
    }
}
