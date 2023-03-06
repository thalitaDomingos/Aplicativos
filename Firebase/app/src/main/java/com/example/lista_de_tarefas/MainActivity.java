package com.example.lista_de_tarefas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth usuario = FirebaseAuth.getInstance();

    private ImageView imageFoto;
    private Button buttonUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonUpload = findViewById(R.id.buttonUpload);
        imageFoto    = findViewById(R.id.imageFoto);

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Configurar para a imagem ser salva em memória
                imageFoto.setDrawingCacheEnabled(true);
                imageFoto.buildDrawingCache();

                // Recuperar bitmap da imagem (imagem a ser carregada)
                Bitmap bitmap = imageFoto.getDrawingCache();

                // Comprimir bitmap para um formato png / jpeg
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);

                // Converter o baos para pixel brutos em uma matriz de bytes (dados da imagem)
                byte[] dadosImagem = baos.toByteArray();

                // Definir nós para o storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("imagens");
                // StorageReference imagens = storageReference.child("imagens").child("foto_perfil");

                // Nome da imagem
                // String nomeArquivo = UUID.randomUUID().toString();
                // StorageReference imagemRef = imagens.child(nomeArquivo + ".jpeg");
                // OU
                StorageReference imagemRef = imagens.child("celular.jpeg");

                // CARREGAR IMAGEM QUE ESTA NO FIREBASE
                Glide.with(MainActivity.this).load(imagemRef).into(imageFoto);

                /*
                // DELETAR IMAGEM DO FIREBASE
                imagemRef.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,
                                "Erro ao deletar ",
                                Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this,
                                "Sucesso ao deletar ",
                                Toast.LENGTH_LONG).show();
                    }
                });

                 */

                /*
                // UPLOAD DA IMAGEM PARA O FIREBASE
                // Retornar objeto que controlará o upload
                UploadTask uploadTask = imagemRef.putBytes(dadosImagem);
                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,
                                "Upload da imagem falhou: " + e.getMessage().toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                Uri url = task.getResult();
                                Toast.makeText(MainActivity.this,
                                        "Sucesso ao fazer upload: " + url.toString(),
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

                 */
            }
        });

        // referencia.child("Pontos").setValue("100");
        // referencia.child("Usuarios").child("001").child("Nome").setValue("Thalita Domingos");

        DatabaseReference usuarios = referencia.child("usuarios");
        DatabaseReference produtos = referencia.child("produtos");
        // DatabaseReference usuarioPesquisa = usuarios.child("-NPrqbIB5AfDankhq-ho");

        // Pesquisa pelo nome
        // Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Ana");

        // Limita a pesquisa nos dois primeiros
        // Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(2);

        // Maior ou igual
        // Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(22);

        // Menor ou igual
        // Query usuarioPesquisa = usuarios.orderByChild("idade").endAt(22);

        // Entre dois valores
        // Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(18).endAt(50);

        // Filtrar palavras -> entre C e V
        // Query usuarioPesquisa = usuarios.orderByChild("nome").startAt("C").endAt("V" + "\uf8ff");

        // Salvando dados no Firebase
        /*

        Usuario usuario = new Usuario();
        usuario.setNome("Thalita");
        usuario.setSobrenome("Domingos");
        usuario.setIdade(22);
        usuarios.child("001").setValue(usuario);

        Produto produto = new Produto();
        produto.setDescricao("Nexus");
        produto.setMarca("LG");
        produto.setPreco(999.99);
        produtos.child("001").setValue(produto);

        // Recuperando dados do Firebase
        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("FIREBASE", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Cadastro de usuario
        usuario.createUserWithEmailAndPassword("thalita2001@hotmail.com", "thalita123")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("CreateUser", "Sucesso ao cadastrar usuário!");
                        } else {
                            Log.i("CreateUser", "Erro ao cadastrar usuário!");
                        }
                    }
                });

        // Verificar se o usuario esta logado
        if (usuario.getCurrentUser() != null) {
            Log.i("CreateUser", "Usuário logado!");
        } else {
            Log.i("CreateUser", "Usuário não logado!");
        }

        // Deslogar usuario
        // usuario.signOut();

        // Logar usuario
        usuario.signInWithEmailAndPassword("thalita@hotmail.com", "thalita123")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("signIn", "Sucesso ao logar usuário!");
                        } else {
                            Log.i("signIn", "Erro ao logar usuário!");
                        }
                    }
                });

        Usuario usuario = new Usuario();
        usuario.setNome("Camile");
        usuario.setSobrenome("Stefany");
        usuario.setIdade(19);
        usuarios.push().setValue(usuario);  // cria identificador unico

        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario dadosUsuario = snapshot.getValue(Usuario.class);
                // Log.i("Dados usuario: ", "Nome: " + dadosUsuario.getNome());
                Log.i("Dados usuario: ", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         */
    }
}