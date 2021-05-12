<?php
/*Affiche le formulaire d'inscription*/
function afficheFormulaire($p,$p_ok,$mp_ok){
    $c = "<div class=\"login-box\">";
    $c .= "<h2>Inscription</h2>";
    $c .= "<form action=\"".htmlspecialchars($_SERVER['PHP_SELF'])."?auth=signup\" method=\"post\">";
    $c .= "<div class=\"user-box\">";
    $c .= "<input type=\"text\" name=\"pseudo\" id=\"pseudo\" pattern=\"\w*\" title=\"Les caractères spéciaux sauf '_' ne sont pas acceptés\" value=\"".$p."\" required><label for=\"pseudo\">PSEUDO</label></div>";
    if ($p_ok == 0){
        $c .= "<p>Ce pseudo est déjà utilisé !</p>";
    }
    $c .= "<div class=\"user-box\"><input type=\"password\" name=\"password\" id=\"password\" minlength=\"6\" title=\"6 caractère minimum\" required><label for=\"password\">MOT DE PASSE</label></div>";
    $c .= "<div class=\"user-box\"><input type=\"password\" name=\"password_confirm\" id=\"password_confirm\" minlength=\"6\" title=\"6 caractère minimum\" required><label for=\"password_confirm\">CONFIRMER LE MOT DE PASSE</label></div>";
    if ($mp_ok == 0){
        $c .= "<p>Les mots de passe ne correspondent pas !</p>";
    }
    $c .= "<input type=\"submit\" class=\"bouton_valid\" title=\"Valider\" value=\"S'inscrire\">";
    $c .= "</form></div>";
    $c .= "<a id=\"dejacc\" href=\"https://mira2.univ-st-etienne.fr/~rg06871s/projet/account/?auth=login\">Vous avez déja un compte ?</a>";
    echo $c;
}

function affiche_body(){
  if(isset($_SESSION['pseudo']) || isset($_SESSION['statut'])){
      echo "<p>Vous êtes connecté !</p>";
      echo "<a href=\"https://mira2.univ-st-etienne.fr/~rg06871s/projet/account/\">Mon compte</a>";
  }else{
      if(isset($_POST['pseudo'],$_POST['password'],$_POST['password_confirm'])){
          include '../php/connex.inc.php';
          $pdo = connex('rg06871s','../');
          try{
             $ps = htmlspecialchars(trim($_POST['pseudo']));
             $stmt = $pdo->prepare("SELECT * FROM membres WHERE pseudo = ?");
             $stmt->execute(array($ps));
             $nb = $stmt->rowCount();
             if($nb==1){
                 afficheFormulaire($ps,0,1);
             }
             else{
                 if(strcmp($_POST['password'],$_POST['password_confirm']) != 0){
                     afficheFormulaire($ps,1,0);
                 }
                 else{
                     $mdp = password_hash(htmlspecialchars(trim($_POST['password'])),PASSWORD_DEFAULT);
                     $stmt->closeCursor();
                     $stmt = $pdo->prepare("INSERT INTO membres (pseudo,mdp,statut) VALUES (?,?,'3')");
                     $stmt->execute(array($ps,$mdp));
                     $nb = $stmt->rowCount();
                     $c = "<div class=\"box_form\">";
                     if($nb==1){
                         $c .= "<p>Vous avez bien été inscrit !</p>";
                         $c .= "<a href=\"https://mira2.univ-st-etienne.fr/~rg06871s/projet/account?auth=login\">Se connecter</a>";
                     }
                     else{
                         $c .= "<p>Erreur lors de l\'ajout</p>";
                     }
                     $c .= "</div>";
                     echo $c;
                 }
             }
             $pdo = null;
          }
          catch(PDOException $e){
              die("Erreur : Problèmes à l'éxécution");
          }
      }
      else{
          afficheFormulaire(null,1,1);
      }
  }
}
?>