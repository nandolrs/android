package br.com.nandolrs.carrorobosuperremoto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
//import br.com.nandolrs.carrorobosuperremoto.lib;

import br.com.nandolrs.carrorobosuperremoto.lib.Cabecalho;
import br.com.nandolrs.carrorobosuperremoto.lib.Rede;

public class MainActivity extends AppCompatActivity {

    int motorDireitoVelocidade = 250;
    int motorEsquerdoVelocidade = 250;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        MotoresInicializar();
    }
    
    void MotoresInicializar()
    {
        TextView inputMotorEsquerdo       = (TextView) findViewById(R.id.inputMotorEsquerdo);
        TextView inputMotorDireito       = (TextView) findViewById(R.id.inputMotorDireito);
        

        
        inputMotorEsquerdo.setText(  motorEsquerdoVelocidade );
        inputMotorDireito.setText(  motorDireitoVelocidade);
    }

    public void IotComandoIr(View v) // go
    {
        IotComando(EN_IOT_COMANDOS.IR);
    }

    public void IotComandoVoltar(View v) // back
    {
        IotComando(EN_IOT_COMANDOS.VOLTAR);
    }

    public void IotComandoDireita(View v) // turn rigth
    {
        IotComando(EN_IOT_COMANDOS.DIREITA);
    }

    public void IotComandoEsquerda(View v) // go back
    {
        IotComando(EN_IOT_COMANDOS.ESQUERDA);
    }

    public void IotComandoLigarDesligar(View v) // go back
    {
        IotComando(EN_IOT_COMANDOS.LIGAR_DESLIGAR);
    }

    public enum EN_IOT_COMANDOS {IR, VOLTAR, DIREITA, ESQUERDA, LIGAR_DESLIGAR, MOTOR_DIREITO_AUMENTAR, MOTOR_DIREITO_REDUZIR,  MOTOR_ESQUERDO_AUMENTAR, MOTOR_ESQUERDO_REDUZIR };

    void IotComando(EN_IOT_COMANDOS comando)
    {

        TextView inputDispositivoDestino  = (TextView) findViewById(R.id.inputDispositivoDestinoIp);
        TextView inputDispositivoRetorno  = (TextView) findViewById(R.id.inputDispositivoRetorno);
        TextView inputMensagem            = (TextView) findViewById(R.id.inputMensagem);
        TextView inputMotorEsquerdo       = (TextView) findViewById(R.id.inputMotorEsquerdo);
        TextView inputMotorDireito       = (TextView) findViewById(R.id.inputMotorDireito);


        String url = inputDispositivoDestino.getText().toString();

        String comandoFrase = "/" + comando.toString();

        if(comando ==  EN_IOT_COMANDOS.MOTOR_ESQUERDO_AUMENTAR  || comando ==  EN_IOT_COMANDOS.MOTOR_ESQUERDO_REDUZIR)
        {
            comandoFrase += "_" + inputMotorEsquerdo;
        }
        else if(comando ==  EN_IOT_COMANDOS.MOTOR_DIREITO_AUMENTAR  || comando ==  EN_IOT_COMANDOS.MOTOR_DIREITO_REDUZIR)
        {
            comandoFrase += "_" + inputMotorDireito;
        }

        EnviarComando(url, comandoFrase, inputDispositivoRetorno);

    }


    void EnviarComando(String url, String comandoFrase, TextView inputSaida)
    {
        try
        {
            //TextView inputUsuario  = (TextView) findViewById(R.id.inputUsuario);
            //TextView inputSenha  = (TextView) findViewById(R.id.inputSenha);
            //TextView inputMensagem = (TextView) findViewById(R.id.inputMensagem);

            inputSaida.setText("Aguarde ....");

            //

/*
            SegCore segCore = new SegCore();

            String usuarioAberto = inputUsuario.getText().toString();
            String usuarioFechado = segCore.Cifrar(usuarioAberto);

            String senhaAberta = inputSenha.getText().toString();
            String senhaFechada = segCore.Cifrar(senhaAberta);;
*/

/*
            org.json.JSONObject credenciais = new org.json.JSONObject();
            credenciais.put("nome",usuarioFechado);
            credenciais.put("senha",senhaFechada ); // senhaFechada
*/

/*
            Spinner inputURL  = (Spinner) findViewById(R.id.inputURL);
            String url = (String) inputURL.getSelectedItem();
*/

            Rede rede = new Rede();
            rede.setUrl(url);

/*
            Cabecalho cabecalho = new Cabecalho();
            cabecalho.nome="w3seguranca.credenciais";
            cabecalho.valor = usuarioFechado + "." + senhaFechada + "." + usuarioFechado + "." + senhaFechada;
*/

            Cabecalho[] cabecalhos = new Cabecalho[0];
/*
            cabecalhos[0] = cabecalho;
*/

            rede.setHeaders(cabecalhos);

            //rede.setJson(credenciinputMensagemais);
            rede.setSaida(inputSaida); // mensagem de saída do sistema
            rede.setMensagem(comandoFrase);
            rede.execute("", null, "");
        }
        catch(Exception e)
        {

        }
        finally
        {

        }
    }

    public void IotComandoMotorEsquerdoAumentar(View v) // go
    {
        if(motorEsquerdoVelocidade + 50 <= 250)
        {
            motorEsquerdoVelocidade += 50;
        }
        MotoresInicializar();

        IotComando(EN_IOT_COMANDOS.MOTOR_ESQUERDO_AUMENTAR);
    }

    public void IotComandoMotorEsquerdoReduzir(View v) // go
    {
        if(motorEsquerdoVelocidade - 50 >= 0)
        {
            motorEsquerdoVelocidade -= 50;
        }
        MotoresInicializar();

        IotComando(EN_IOT_COMANDOS.MOTOR_ESQUERDO_REDUZIR);
    }

    public void IotComandoMotorDireitoAumentar(View v) // go
    {
        if(motorDireitoVelocidade + 50 <= 250)
        {
            motorDireitoVelocidade += 50;
        }
        MotoresInicializar();

        IotComando(EN_IOT_COMANDOS.MOTOR_DIREITO_AUMENTAR);
    }

    public void IotComandoMotorDireitoReduzir(View v) // go
    {
        if(motorDireitoVelocidade - 50 >= 0)
        {
            motorDireitoVelocidade -= 50;
        }
        MotoresInicializar();

        IotComando(EN_IOT_COMANDOS.MOTOR_DIREITO_REDUZIR);
    }
}
