package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.ui.dashboard

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.Area
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.CosasUtiles
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.CosasUtiles.arregloArea
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.CosasUtiles.arregloSub
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.CosasUtiles.idInfo
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.CosasUtiles.idInfo2
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.CosasUtiles.modoDivDesc
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.Subdepartamento
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_gustavo_marin_lemus.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var auxiliar =  Area(requireContext())
        var auxiliar2 =  Subdepartamento(requireContext())
        idInfo = -1
        binding.listaArea.adapter  = ArrayAdapter<String>(requireContext(),
            R.layout.simple_expandable_list_item_1,auxiliar.mostrarTodos())
        binding.listaDepate.adapter  = ArrayAdapter<String>(requireContext(),
            R.layout.simple_expandable_list_item_1,auxiliar2.mostrarTodos())
        binding.btndesdiv.setOnClickListener {
            if (modoDivDesc.equals("Descripcion")){
                binding.listaArea.adapter  = ArrayAdapter<String>(requireContext(),
                    R.layout.simple_expandable_list_item_1,auxiliar.mostrarTodosDiv())
                modoDivDesc = "Division"
                binding.btndesdiv.setText("Descripción")
            }else{
                binding.listaArea.adapter  = ArrayAdapter<String>(requireContext(),
                    R.layout.simple_expandable_list_item_1,auxiliar.mostrarTodos())
                modoDivDesc = "Descripcion"
                binding.btndesdiv.setText("División")
            }
        }
        binding.listaArea.setOnItemClickListener { adapterView, view, i, l ->
            idInfo = arregloArea.get(i).idArea
        }
        binding.btnAgregar.setOnClickListener {
            if (idInfo == -1){
                Toast.makeText(requireContext(),"¡ELIJA UNA ÁREA!", Toast.LENGTH_LONG).show()
            }else{
                auxiliar2.idEdificio = binding.ediEdicio.text.toString()
                auxiliar2.piso = binding.ediPiso.text.toString()
                auxiliar2.idArea = idInfo
                if(auxiliar2.insertar()){
                    Toast.makeText(requireContext(),"¡SE AGREGO DE MANERA CORRECTA!", Toast.LENGTH_LONG).show()
                    binding.ediEdicio.setText("")
                    binding.ediPiso.setText("")
                    binding.listaDepate.adapter  = ArrayAdapter<String>(requireContext(),
                        R.layout.simple_expandable_list_item_1,auxiliar2.mostrarTodos())
                    idInfo == -1
                }
            }
        }
        binding.listaDepate.setOnItemClickListener { adapterView, view, i, l ->
            idInfo2 = arregloSub.get(i).idSubDepto
            binding.ediEdicio.setText(arregloSub.get(i).idEdificio.toString())
            binding.ediPiso.setText (arregloSub.get(i).piso.toString())
            var arreglo1 = auxiliar.obtenerDatos(arregloSub.get(i).idArea.toString())
            binding.idArea.setText("IdArea: "+ arregloSub.get(i).idArea.toString())
            binding.desc.setText("Descripción: ${arreglo1.get(0)}")
            binding.divi.setText("División: ${arreglo1.get(1)}")
            binding.empleados.setText("Cantidad Empleados: ${arreglo1.get(2)}")

        }
        binding.btnActualiza.setOnClickListener {
            if(idInfo2 == -1) {
                Toast.makeText(
                    requireContext(),
                    "¡ELIJA UN REGISTRO A ACTUALIZAR DEL LISTADO!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (idInfo == -1) {
                Toast.makeText(
                    requireContext(),
                    "¡ELIJA UNA AREA DEL LISTADO!",
                    Toast.LENGTH_LONG
                ).show()
            }else {
                auxiliar2.idSubDepto = idInfo2
                auxiliar2.idEdificio = binding.ediEdicio.text.toString()
                auxiliar2.piso = binding.ediPiso.text.toString()
                auxiliar2.idArea = idInfo
                if (auxiliar2.actualizar()) {
                    Toast.makeText(
                        requireContext(),
                        "¡ACTUALIZACIÓN COMPLETADA!",
                        Toast.LENGTH_LONG
                    )
                        .show()
                    binding.listaDepate.adapter = ArrayAdapter<String>(
                        requireContext(),
                        android.R.layout.simple_expandable_list_item_1,
                        auxiliar2.mostrarTodos()
                    )
                    binding.ediEdicio.setText("")
                    binding.ediPiso.setText("")
                    idInfo2 = -1
                    idInfo = -1
                }
            }
        }
        binding.btneliminar.setOnClickListener {
            if(idInfo2 == -1) {
                Toast.makeText(
                    requireContext(),
                    "¡ELIJA UN REGISTRO A ELIMINAR DEL LISTADO!",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                auxiliar2.idSubDepto = idInfo2
                if (auxiliar2.eliminar()) {
                    Toast.makeText(requireContext(), "¡ELIMINACIÓN COMPLETADA!", Toast.LENGTH_LONG)
                        .show()
                    binding.listaDepate.adapter = ArrayAdapter<String>(
                        requireContext(),
                        android.R.layout.simple_expandable_list_item_1,
                        auxiliar2.mostrarTodos()
                    )
                    binding.ediEdicio.setText("")
                    binding.ediPiso.setText("")
                    idInfo2 = -1
                }
            }

        }
        binding.btnBuscar.setOnClickListener {
            if(binding.ediEdicio.text.toString().equals("")){
                if(binding.descEs.text.toString().equals("")){
                    if (binding.divii.text.toString().equals("")){
                        binding.listaDepate.adapter = ArrayAdapter<String>(
                            requireContext(),
                            android.R.layout.simple_expandable_list_item_1,
                            auxiliar2.mostrarTodos())
                        Toast.makeText(requireContext(), "MOSTRANDO TODOS LOS DATOS", Toast.LENGTH_LONG).show()
                    }else{
                        binding.listaDepate.adapter  = ArrayAdapter<String>(requireContext(),android.R.layout.simple_expandable_list_item_1,auxiliar2.mostrarDivi(binding.divii.text.toString()))
                        Toast.makeText(requireContext(), "MOSTRANDO DATOS POR DIVISIÓN", Toast.LENGTH_LONG).show()
                    }
                }else{
                    binding.listaDepate.adapter  = ArrayAdapter<String>(requireContext(),android.R.layout.simple_expandable_list_item_1,auxiliar2.mostrarDesc(binding.descEs.text.toString()))
                    Toast.makeText(requireContext(), "MOSTRANDO DATOS POR DESCRIPCIÓN", Toast.LENGTH_LONG).show()
                }
            }else{
                binding.listaDepate.adapter  = ArrayAdapter<String>(requireContext(),android.R.layout.simple_expandable_list_item_1,auxiliar2.mostrarIdEdificio(binding.ediEdicio.text.toString()))
                Toast.makeText(requireContext(), "MOSTRANDO DATOS POR EDIFICIO", Toast.LENGTH_LONG).show()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}